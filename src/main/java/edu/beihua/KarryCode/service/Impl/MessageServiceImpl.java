package edu.beihua.KarryCode.service.Impl;

import edu.beihua.KarryCode.DBCon.DBCon;
import edu.beihua.KarryCode.entity.Admin;
import edu.beihua.KarryCode.entity.Customer;
import edu.beihua.KarryCode.entity.Message;
import edu.beihua.KarryCode.mapper.ICustomerMapper;
import edu.beihua.KarryCode.mapper.IMessageMapper;
import edu.beihua.KarryCode.repositoryMongo.IMessageLogRepMongo;
import edu.beihua.KarryCode.service.IMessageService;
import edu.beihua.KarryCode.tools.AccountUtility;
import edu.beihua.KarryCode.tools.MessageActivity;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static edu.beihua.KarryCode.Command_Control.CommandView.examine;
@Service
public class MessageServiceImpl implements IMessageService {
    DBCon dbCon = new DBCon();
    SqlSessionFactory sqlSessionFactory = dbCon.sqlSessionFactory();

    Scanner scanner = new Scanner(System.in);
    @Autowired
    IMessageLogRepMongo iMessageLogRepMongo;

    @Override
    public void showAllMessage() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IMessageMapper iMessageMapper = sqlSession.getMapper(IMessageMapper.class);

        System.out.println("————————————————————————————————————————————————————————————————————————————————————————");
        List<Message> messages = iMessageMapper.selectAllMessage();
        for (Message message : messages) {
            System.out.println("    |   id:"+ message.getMessage_id());
            System.out.println("    |   留言人:"+message.getCustomer_name());
            System.out.println("    |   留言日期:"+message.getMessage_date());
            System.out.println("    |   留言内容:"+message.getMessage_content());
            System.out.println("————————————————————————————————————————————————————————————————————————————————————————");
        }
        System.out.println("————————————————————————————————————————————————————————————————————————————————————————");
        iMessageLogRepMongo.Log_VisitorViewAllMessages();
        AccountUtility.readReturn();
    }
//    @Autowired
//    MongoTemplate mongoTemplate;
    @Override
    public void showAllMessageAll(Admin admin) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IMessageMapper iMessageMapper = sqlSession.getMapper(IMessageMapper.class);

        System.out.println("————————————————————————————————————————————————————————————————————————————————————————");
        List<Message> messages = iMessageMapper.selectAllMessageAll();
        for (Message message : messages) {
            System.out.println("    |   id:"+ message.getMessage_id());
            System.out.println("    |   留言人:"+message.getCustomer_name());
            System.out.println("    |   留言日期:"+message.getMessage_date());
            System.out.println("    |   留言内容:"+message.getMessage_content());
            if (message.isMessage_state()){
                System.out.println("    |   展示状态：展示");
            }else {
                System.out.println("    |   展示状态：隐藏");

            }
            System.out.println("————————————————————————————————————————————————————————————————————————————————————————");
        }
        System.out.println("————————————————————————————————————————————————————————————————————————————————————————");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    }



    @Override
    public boolean somethingToSay(Customer customer) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IMessageMapper iMessageMapper = sqlSession.getMapper(IMessageMapper.class);

        System.out.println("话筒递给你：");
        String customerSay = scanner.next();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = simpleDateFormat.format(date);

        try {
            int i = iMessageMapper.insertMessage(customer.getName(), nowDate, customerSay);
            if (i>0){
                System.out.println("\n" +
                        "\033[92m                                 \n" +
                        " ___ _   _  ___ ___ ___  ___ ___ \n" +
                        "/ __| | | |/ __/ __/ _ \\/ __/ __|\n" +
                        "\\__ \\ |_| | (_| (_|  __/\\__ \\__ \\\n" +
                        "|___/\\__,_|\\___\\___\\___||___/___/\n" +
                        "                                 \033[0m\n");
                System.out.println("提交成功");
                sqlSession.commit();
                iMessageLogRepMongo.Log_CustomerPublishMessage(customer.getName(),true);
                return true;
            }
        }catch (Exception e){
            sqlSession.rollback();
            e.printStackTrace();
            System.out.println("失败");
            iMessageLogRepMongo.Log_CustomerPublishMessage(customer.getName(),false);
        }
        return false;
    }

    @Override
    public boolean commentControl(Admin admin) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IMessageMapper iMessageMapper = sqlSession.getMapper(IMessageMapper.class);

        ICustomerMapper iCustomerMapper = sqlSession.getMapper(ICustomerMapper.class);

        if (admin==null){
            System.out.println("|抱歉你没有此权限！");
            return false;
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        showAllMessageAll(admin);
        iMessageLogRepMongo.Log_AdminViewMessage(admin.getName());
//        IMessageLog iMessageLog = new MessageLogImpl();
//        iMessageLog.Log_AdminViewMessage(admin.getName());
        System.out.println("1.指定评论id，控制评论展示状态       2.指定评论id，删除评论       3.指定用户名，删除其评论       4:退出");
        System.out.println("请输入：");
        String select = scanner.next();

        if (select.matches("-?\\d+(\\.\\d+)?")) {
                /*
                "-" 表示可选的负号，即数字可以是负数。
                "\d+" 表示至少一个数字。
                "(\.\d+)?" 表示小数点及其后面的数字是可选的，即数字可以是整数或小数。
                因此，这个正则表达式可以匹配的数字格式包括整数、带小数点的实数、以及带正负号的这两种类型的数。
                如果字符串符合这个格式，那么它就会返回 true。
                * */
            System.out.println("|等待系统响应");
        } else {
            examine(select);
            return false;
        }
        int select2;
        try {
            select2=Integer.parseInt(select);
        }catch (NumberFormatException e) {
            select2 = 0;
            System.out.println("|不带你这么玩的奥！");
        }
        switch (select2){
            case 1:{
                System.out.println("请输入评论id");
                int id = AccountUtility.readInt();
                Message message = iMessageMapper.selectById(id);
                if (message==null){
                    System.out.println("评论不存在！");
                    return false;
                }
                System.out.println("请设置展示状态：");
                System.out.println("1.展示        2.取消展示");
                boolean show;
                int select3 = AccountUtility.readInt();
                try{
                    switch (select3){
                        case 1:{
                            iMessageMapper.updateStaById(id,true);
                            sqlSession.commit();
                            iMessageLogRepMongo.Log_AdminModifyMessageStatus(admin.getName(), MessageActivity.Display,true);
                            break;
                            //zs
                        }
                        case 2:{
                            iMessageMapper.updateStaById(id,false);
                            sqlSession.commit();
                            iMessageLogRepMongo.Log_AdminModifyMessageStatus(admin.getName(), MessageActivity.Hide,true);
                            break;
                            //bb
                        }
                        default:{
                            System.out.println("不正确的选择！");
                            iMessageLogRepMongo.Log_AdminModifyMessageStatus(admin.getName(), null,false);

                            return false;
                        }
                    }
                    System.out.println("\n" +
                            "\033[92m                                 \n" +
                            " ___ _   _  ___ ___ ___  ___ ___ \n" +
                            "/ __| | | |/ __/ __/ _ \\/ __/ __|\n" +
                            "\\__ \\ |_| | (_| (_|  __/\\__ \\__ \\\n" +
                            "|___/\\__,_|\\___\\___\\___||___/___/\n" +
                            "                                 \033[0m\n");
                    System.out.println("修改成功！！");
                } catch (Exception e) {
                    System.out.println(e.getCause());
                    sqlSession.rollback();
                    System.out.println("修改失败！！");
                    iMessageLogRepMongo.Log_AdminModifyMessageStatus(admin.getName(), null,false);

                }
                break;
            }
            case 2:{
                System.out.println("请输入评论id");
                int id = AccountUtility.readInt();
                Message message = iMessageMapper.selectById(id);
                if (message==null){
                    System.out.println("评论不存在！");
                    iMessageLogRepMongo.Log_AdminModifyMessageStatus(admin.getName(), MessageActivity.Delete,false);

                    return false;
                }
                System.out.println("确定删除吗？");
                if (AccountUtility.readConfirmSelection()=='Y') {
                    System.out.println("|好的");

                    try {
                        iMessageMapper.deleteMessage(id);
                        sqlSession.commit();
                        System.out.println("\n" +
                                "\033[92m                                 \n" +
                                " ___ _   _  ___ ___ ___  ___ ___ \n" +
                                "/ __| | | |/ __/ __/ _ \\/ __/ __|\n" +
                                "\\__ \\ |_| | (_| (_|  __/\\__ \\__ \\\n" +
                                "|___/\\__,_|\\___\\___\\___||___/___/\n" +
                                "                                 \033[0m\n");
                        iMessageLogRepMongo.Log_AdminModifyMessageStatus(admin.getName(), MessageActivity.Delete,true);

                    } catch (Exception e) {
                        sqlSession.rollback();
                        System.out.println(e);
                        iMessageLogRepMongo.Log_AdminModifyMessageStatus(admin.getName(), MessageActivity.Delete,false);

                    }
                }

                break;
            }
            case 3:{
                System.out.println("请输入用户名：");
                String name = scanner.next();
                Customer customer = iCustomerMapper.selectCustomerAcc(name);
                if (customer==null){
                    System.out.println("用户名不存在，无法控制");
                    iMessageLogRepMongo.Log_AdminModifyMessageStatus(admin.getName(), null,false);

                    return false;
                }
                System.out.println("确定删除吗？");
                if (AccountUtility.readConfirmSelection()=='Y') {
                    System.out.println("|好的");
                    try{
                        iMessageMapper.deleteMessageByCName(name);
                        sqlSession.commit();
                        System.out.println("\n" +
                                "\033[92m                                 \n" +
                                " ___ _   _  ___ ___ ___  ___ ___ \n" +
                                "/ __| | | |/ __/ __/ _ \\/ __/ __|\n" +
                                "\\__ \\ |_| | (_| (_|  __/\\__ \\__ \\\n" +
                                "|___/\\__,_|\\___\\___\\___||___/___/\n" +
                                "                                 \033[0m\n");
                        System.out.println("成功");
                        iMessageLogRepMongo.Log_AdminModifyMessageStatus(admin.getName(), MessageActivity.DeleteMany,true);

                    } catch (Exception e) {
                        sqlSession.rollback();
                        System.out.println(e.getCause());
                        System.out.println("失败");
                        iMessageLogRepMongo.Log_AdminModifyMessageStatus(admin.getName(), MessageActivity.DeleteMany,false);


                    }
                }
                break;
            }
            case 4:{
                return false;
            }
        }
        return false;

    }
}
