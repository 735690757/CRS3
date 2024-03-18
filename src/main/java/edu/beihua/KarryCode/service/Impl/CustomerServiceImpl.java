package edu.beihua.KarryCode.service.Impl;

import edu.beihua.KarryCode.Command_Control.CommandView;
import edu.beihua.KarryCode.DBCon.DBCon;
import edu.beihua.KarryCode.entity.Admin;
import edu.beihua.KarryCode.entity.Customer;
import edu.beihua.KarryCode.mapper.ICustomerMapper;
import edu.beihua.KarryCode.repositoryMongo.IUserRepMongo;
import edu.beihua.KarryCode.service.ICustomerService;
import edu.beihua.KarryCode.tools.AccountUtility;
import edu.beihua.KarryCode.tools.EmailValidator;
import edu.beihua.KarryCode.tools.Permissions;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    IUserRepMongo iUserRepMongo;
    DBCon dbCon = new DBCon();

    SqlSessionFactory sqlSessionFactory = dbCon.sqlSessionFactory();

    Scanner scanner = new Scanner(System.in);
    EmailValidator emailValidator = new EmailValidator();
    public<T> boolean examine(T s){
        String ss=(String) s;
        if (new CommandView().Command(ss)==1){
            System.out.println("控制级主动中断");
            AccountUtility.readReturn();
            return true;
        }
        return false;
    }
    @Override
    public Customer Login() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ICustomerMapper iCustomerMapper = sqlSession.getMapper(ICustomerMapper.class);

        System.out.println("——————————————————用户登录——————————————————");
        System.out.println("请输入账号：");
        String name = scanner.next();
        if (this.examine(name)){
            return null;
        }
        System.out.println("请输入密码：");
        String pass = scanner.next();
        if (this.examine(pass)){
            return null;
        }
        Customer customer = iCustomerMapper.selectBy_CName_CPass(name, pass);
        if (customer==null){
            System.out.println("用户名不存在或密码错误");
            // * @Author KarryLiu_刘珂瑞 * @Description TODO MongoDB记录登录失败日志
            iUserRepMongo.Log_UserLoginFailure(name,Permissions.VISITOR);
            return null;
        }else {
            System.out.println("");
            System.out.println("欢迎:"+customer.getName()+"回来！");
            // * @Author KarryLiu_刘珂瑞 * @Description TODO MongoDB记录成功登录日志
            iUserRepMongo.Log_UserLoginSuccess(customer, Permissions.CUSTOMER);
            return customer;
        }
    }

    @Override
    public boolean Register() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ICustomerMapper iCustomerMapper = sqlSession.getMapper(ICustomerMapper.class);

        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("———————————注册开始———————————");
            String name;
            String pass;
            String repass;
            String email;
            String phone;
            System.out.println("请输入用户名：");
            name = scanner.next();
            if (this.examine(name)){
                return false;
            }

            while (true){
                System.out.println("请设置一个密码");
                pass = scanner.next();
                if (this.examine(pass)){
                    return false;
                }
                System.out.println("请再次确认密码");
                repass = scanner.next();
                if (this.examine(repass)){
                    return false;
                }
                if (repass.equals(pass)){
                    System.out.println("OK!");
                    break;
                }else {
                    System.out.println("我坚信你一定是摁错了，两次密码不匹配请重新输入（づ￣3￣）");
                    Thread.sleep(1000);
                }
            }
            while (true){
                System.out.println("请输入11位手机号：");
                phone = scanner.next();
                if (this.examine(phone)){
                    return false;
                }
                if (phone.length()==11){
                    break;
                }else {
                    System.out.println("不是正确的手机号╮(๑•́ ₃•̀๑)╭");
                }
            }
            while (true){
                System.out.println("请输入邮箱：（格式：XXX.@XXX.com）");
                email = scanner.next();
                if (this.examine(email)){
                    return false;
                }
                if (emailValidator.validate(email)){
                    break;
                }else {
                    System.out.println("格式不正确，请重新输入(´•灬•‘)");
                    AccountUtility.readReturn();
                }
            }
            Customer toSigIn_Customer = new Customer(name,pass,email,phone,0);
            boolean b;
            try{
                b = iCustomerMapper.insertNewCustomer(
                        toSigIn_Customer.getName(),
                        toSigIn_Customer.getPassword(),
                        toSigIn_Customer.getCustomer_Email(),
                        toSigIn_Customer.getCustomer_Phone(),
                        toSigIn_Customer.getCustomer_money());
            } catch (Exception e) {
                b=false;
            }

            if (b){
                sqlSession.commit();
                System.out.println("\n" +
                        "                                 \n" +
                        " ___ _   _  ___ ___ ___  ___ ___ \n" +
                        "/ __| | | |/ __/ __/ _ \\/ __/ __|\n" +
                        "\\__ \\ |_| | (_| (_|  __/\\__ \\__ \\\n" +
                        "|___/\\__,_|\\___\\___\\___||___/___/\n" +
                        "                                 \n");
                System.out.println("注册成功！！");
                return true;
            }else {
                sqlSession.rollback();
                System.out.println("注册失败！！用户名已经存在");
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void howMuchMoneyDoIHave(Customer customer) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ICustomerMapper iCustomerMapper = sqlSession.getMapper(ICustomerMapper.class);

        System.out.println("————————————————我有多少钱？————————————————");
        double money = iCustomerMapper.selectMoneyBY_CName(customer.getName());
        System.out.println("您有："+money+"元");
        AccountUtility.readReturn();
        return;
    }

    @Override
    public boolean chargeOrRecharge(Customer customer, int num) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ICustomerMapper iCustomerMapper = sqlSession.getMapper(ICustomerMapper.class);

        if (num==1){
            System.out.println("请输入充值的钱数：");
            double moneyCharge = scanner.nextDouble();
            boolean b = false;
            customer.setCustomer_money(customer.getCustomer_money() + moneyCharge);
            try{
                b = iCustomerMapper.updateMoneyBy_CName(customer.getName(), customer.getCustomer_money());

            } catch (Exception e) {
                System.out.println("\033[31m" +
                        "\n" +
                        "  __       _ _                \n" +
                        " / _| __ _(_) |_   _ _ __ ___ \n" +
                        "| |_ / _` | | | | | | '__/ _ \\\n" +
                        "|  _| (_| | | | |_| | | |  __/\n" +
                        "|_|  \\__,_|_|_|\\__,_|_|  \\___|\n" +
                        "                              \n"+
                        "\033[0m");
                System.out.println("充值失败");
                sqlSession.rollback();
                return false;
            }
            if (b){
                System.out.println("\n" +
                        "\033[92m                                 \n" +
                        " ___ _   _  ___ ___ ___  ___ ___ \n" +
                        "/ __| | | |/ __/ __/ _ \\/ __/ __|\n" +
                        "\\__ \\ |_| | (_| (_|  __/\\__ \\__ \\\n" +
                        "|___/\\__,_|\\___\\___\\___||___/___/\n" +
                        "                                 \033[0m\n");
                System.out.println("充值成功");

                sqlSession.commit();
                double v = iCustomerMapper.selectMoneyBY_CName(customer.getName());
                double pv = v-moneyCharge;
                System.out.println("充值前："+pv);
                System.out.println("充值后："+v);
                AccountUtility.readReturn();
                return true;
            }


        } else if (num==2){
            System.out.println("请输入取出的钱数：");
            double moneyCharge = scanner.nextDouble();
            boolean b = false;
            if (customer.getCustomer_money()-moneyCharge<0){
                System.out.println("\033[31m" +
                        "\n" +
                        "  __       _ _                \n" +
                        " / _| __ _(_) |_   _ _ __ ___ \n" +
                        "| |_ / _` | | | | | | '__/ _ \\\n" +
                        "|  _| (_| | | | |_| | | |  __/\n" +
                        "|_|  \\__,_|_|_|\\__,_|_|  \\___|\n" +
                        "                              \n"+
                        "\033[0m");
                System.out.println("没钱啦！！");
                AccountUtility.readReturn();
                return false;
            }
            customer.setCustomer_money(customer.getCustomer_money() - moneyCharge);
            try{
                b = iCustomerMapper.updateMoneyBy_CName(customer.getName(), customer.getCustomer_money());

            } catch (Exception e) {
                System.out.println("取钱失败");
                sqlSession.rollback();
                return false;
            }
            if (b){
                System.out.println("\n" +
                        "\033[92m                                 \n" +
                        " ___ _   _  ___ ___ ___  ___ ___ \n" +
                        "/ __| | | |/ __/ __/ _ \\/ __/ __|\n" +
                        "\\__ \\ |_| | (_| (_|  __/\\__ \\__ \\\n" +
                        "|___/\\__,_|\\___\\___\\___||___/___/\n" +
                        "                                 \033[0m\n");
                System.out.println("取钱成功");
                sqlSession.commit();
                double v = iCustomerMapper.selectMoneyBY_CName(customer.getName()) + moneyCharge;
                System.out.println("取前："+v);
                System.out.println("取后："+iCustomerMapper.selectMoneyBY_CName(customer.getName()));
                AccountUtility.readReturn();
                return true;
            }
        }
        return false;
    }

    @Override
    public void queryByCustomerName_fuzzy(Admin admin) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ICustomerMapper iCustomerMapper = sqlSession.getMapper(ICustomerMapper.class);

        if (admin==null){
            System.out.println("|抱歉你没有此权限！");
            AccountUtility.readReturn();
            return;
        }
        String fuzzName;
        System.out.println("|请输入用户名：");
        fuzzName=scanner.next();
        List<Customer> customers = iCustomerMapper.queryByCustomerName_fuzzy(fuzzName);
        if (customers.size()==0){
            System.out.println("没找到");
            AccountUtility.readReturn();
        }else {
            IterCustomerList(customers);
        }
        AccountUtility.readReturn();

    }

    @Override
    public void queryAllCustomer(Admin admin) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ICustomerMapper iCustomerMapper = sqlSession.getMapper(ICustomerMapper.class);

        if (admin==null){
            System.out.println("|抱歉你没有此权限！");
            AccountUtility.readReturn();
            return;
        }
        int count = iCustomerMapper.queryCustomerCount();
        List<Customer> customers = iCustomerMapper.selectAllCustomer();

        IterCustomerList(customers);
        System.out.println("—————————————————————————————");
        String str = "    |                               查询完成，本系统中总共存有用户数量为 "+count+" 人"+"\n    |                               莫愁前途无知己，天下谁人不识君。";
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            System.out.print(aChar);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }
        }
        System.out.println("");
        AccountUtility.readReturn();
    }

    @Override
    public boolean CustomerInformationModified(Admin admin) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ICustomerMapper iCustomerMapper = sqlSession.getMapper(ICustomerMapper.class);

        if (admin==null){
            System.out.println("|管理员未登录！！");
            return false;
        }
        System.out.println("|请输入用户名（必须为精确名称）：");
        String userName = scanner.next();
        Customer customer = iCustomerMapper.selectCustomerAcc(userName);
        if (customer==null){
            System.out.println("|未找到该用户！！！");
            return false;
        }else {
            System.out.println("—————————————————————————————");
            System.out.println("    |   ID："+customer.getId());
            System.out.println("    |   用户名："+customer.getName());
            System.out.println("    |   密码："+customer.getPassword());
            System.out.println("    |   电子邮箱："+customer.getCustomer_Email());
            System.out.println("    |   手机号："+customer.getCustomer_Phone());
            System.out.println("    |   余额："+customer.getCustomer_money());
            System.out.println("—————————————————————————————");

            String bakName = customer.getName();
            int adminSelect;
            boolean cyclicStatus=false;
            boolean ifChange=false;
            while (true){
                if (cyclicStatus){
                    break;
                }
                System.out.println("1. 修改用户名    2. 修改密码     3.修改邮箱      4.修改手机号     5. 没法改钱 别想了 哼哼   6. 退出修改     7. 提交修改");
                System.out.println("|请输入（您可以输入” 8 “以修改用户ID，但不建议这么干）：");
                adminSelect = AccountUtility.readIntU(6);
                switch (adminSelect){
                    case 1:{
                        System.out.println("————————修改用户名————————");
                        String newName;
                        while (true){
                            System.out.println("|请输入新的用户名：");
                            newName = scanner.next();
                            if (this.examine(newName)){
                                break;
                            }
                            Customer customerX = iCustomerMapper.selectCustomerAcc(newName);
                            try {
                                if (customerX!=null){
                                    System.out.println("|已经有人注册过这个名字了！");
                                    AccountUtility.readReturn();
                                }else {
                                    System.out.println("|okok!");
                                    System.out.println("|旧用户名："+customer.getName());
                                    System.out.println("|新用户名："+newName);
                                    System.out.println("|确认修改吗？");
                                    char c = AccountUtility.readConfirmSelection();
                                    if (c=='Y'){
                                        System.out.println("|好的");
                                        customer.setName(newName);
                                        ifChange=true;
                                    }else{
                                        System.out.println("|那您再看看");
                                    }
                                    break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                        break;
                    }
                    case 2:{
                        System.out.println("————————————————修改密码————————————————");
                        System.out.println("|请输入新密码：");
                        String newPass = scanner.next();
                        System.out.println("|您输入的是："+newPass+"   确认吗？");
                        if (AccountUtility.readConfirmSelection()=='Y'){
                            System.out.println("|好的");
                            customer.setPassword(newPass);
                            ifChange=true;
                        }else {
                            System.out.println("|那您再看看");
                        }
                        AccountUtility.readReturn();
                        break;
                    }
                    case 3:{
                        System.out.println("————————————————修改邮箱————————————————");
                        String newEmail;
                        while (true){
                            System.out.println("|请输入新邮箱：（格式：XXX.@XXX.com）");
                            newEmail = scanner.next();
                            if (emailValidator.validate(newEmail)){
                                break;
                            }else {
                                System.out.println("|格式不正确，请重新输入");
                                AccountUtility.readReturn();
                            }
                        }
                        System.out.println("|您输入的是："+newEmail+"   确认吗？");
                        if (AccountUtility.readConfirmSelection()=='Y'){
                            System.out.println("|好的");
                            customer.setCustomer_Email(newEmail);
                            ifChange=true;


                        }else {
                            System.out.println("|那您再看看");
                        }
                        AccountUtility.readReturn();
                        break;
                    }
                    case 4:{
                        System.out.println("————————————————修改手机号————————————————");
                        String newPhone;
                        while (true){
                            System.out.println("|请输入新手机号：");
                            newPhone = scanner.next();
                            if (newPhone.length()==11){
                                break;
                            }else {
                                System.out.println("|不是正确的手机号");
                            }
                        }
                        System.out.println("|您输入的是："+newPhone+"   确认吗？");
                        if (AccountUtility.readConfirmSelection()=='Y'){
                            System.out.println("|好的");
                            customer.setCustomer_Phone(newPhone);
                            ifChange=true;


                        }else {
                            System.out.println("|那您再看看");
                        }
                        AccountUtility.readReturn();
                        break;
                    }
                    case 5:{
                        System.out.println("|别看了，真没有");
                        AccountUtility.readReturn();
                        break;
                    }
                    case 6:{
                        AccountUtility.readReturn();
                        if (ifChange){
                            System.out.println("您已经修改了一些信息，这一操作执行后将不会提交，确定退出吗？");
                            if (AccountUtility.readConfirmSelection()=='Y') {
                                System.out.println("|好的");
                                cyclicStatus=true;
                                System.out.println("|退出");
                            }else {
                                break;
                            }
                        }else {
                            cyclicStatus=true;
                            System.out.println("|退出");
                        }

                        break;
                    }
                    case 7:{
                        System.out.println("————————————————提交修改————————————————");
                        int i = iCustomerMapper.updateCustomerAll_ByName(
                                customer.getName(),
                                customer.getPassword(),
                                customer.getCustomer_Email(),
                                customer.getCustomer_Phone(),
                                bakName
                        );
                        if (i>0){
                            sqlSession.commit();
                            System.out.println("\n" +
                                    "\033[92m                                 \n" +
                                    " ___ _   _  ___ ___ ___  ___ ___ \n" +
                                    "/ __| | | |/ __/ __/ _ \\/ __/ __|\n" +
                                    "\\__ \\ |_| | (_| (_|  __/\\__ \\__ \\\n" +
                                    "|___/\\__,_|\\___\\___\\___||___/___/\n" +
                                    "                                 \033[0m\n");
                            System.out.println("|提交成功！！");
                            ifChange=false;
                        }else {
                            sqlSession.rollback();
                            System.out.println("|修改失败");
                        }
                        AccountUtility.readReturn();
                        break;
                    }
                    default:{
                        System.out.println("|????就你？还管理员呢？！！");
                        break;
                    }
                }
            }








        }


        return false;
    }

    @Override
    public boolean forgotPassword() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ICustomerMapper iCustomerMapper = sqlSession.getMapper(ICustomerMapper.class);

        System.out.println("请输入用户名：");
        String name = scanner.next();
        Customer customer = iCustomerMapper.selectCustomerAcc(name);
        if (customer==null){
            System.out.println("该用户不存在！");
            return false;
        }else {
            System.out.println("已经找到！");
            System.out.println("请输入手机号后四位：");
            String lastFourDigits;
            int Reliability=3;
            while (true){
                lastFourDigits = AccountUtility.readString(4);
                if (lastFourDigits.equals(customer.getCustomer_Phone().substring(7))){
                    System.out.println("好的！");
                    System.out.println("请输入新密码：");
                    String newPass = scanner.next();
                    customer.setPassword(newPass);
                    //System.out.println(customer.getPass());
                    int i = 0;
                    try{
                        i = iCustomerMapper.updateCustomerAll_ByName(
                                customer.getName(),
                                customer.getPassword(),
                                customer.getCustomer_Email(),
                                customer.getCustomer_Phone(),
                                customer.getName()
                        );
                    } catch (Exception e) {
                        sqlSession.rollback();
                        System.out.println("修改失败，SQl异常");
                    }
                    if (i>0){
                        sqlSession.commit();
                        System.out.println("\n" +
                                "\033[92m                                 \n" +
                                " ___ _   _  ___ ___ ___  ___ ___ \n" +
                                "/ __| | | |/ __/ __/ _ \\/ __/ __|\n" +
                                "\\__ \\ |_| | (_| (_|  __/\\__ \\__ \\\n" +
                                "|___/\\__,_|\\___\\___\\___||___/___/\n" +
                                "                                 \033[0m\n");
                        System.out.println("修改成功！！");
                    }
                    AccountUtility.readReturn();
                    return true;
                }else {
                    System.out.println("匹配失败，请重新输入！");
                    Reliability--;
                }
                if (Reliability==0){
                    System.out.println("错误次数过多！");
                    AccountUtility.readReturn();
                    return false;
                }
            }
        }
    }

    @Override
    public boolean forgotUsername() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ICustomerMapper iCustomerMapper = sqlSession.getMapper(ICustomerMapper.class);

        String phone;
        while (true){
            System.out.println("请输入11位手机号：");
            phone = scanner.next();
            if (this.examine(phone)){
                return false;
            }
            if (phone.length()==11){
                break;
            }else {
                System.out.println("不是正确的手机号╮(๑•́ ₃•̀๑)╭");
            }
        }
        String email;
        while (true){
            System.out.println("请输入邮箱：（格式：XXX.@XXX.com）");
            email = scanner.next();
            if (this.examine(email)){
                return false;
            }
            if (emailValidator.validate(email)){
                break;
            }else {
                System.out.println("格式不正确，请重新输入(´•灬•‘)");
                AccountUtility.readReturn();
            }
        }
        String s = iCustomerMapper.forgotUsername_SelectUsername(phone, email);
        if (s == null) {
            System.out.println("手机号/邮箱或都不正确，请检查！");
            AccountUtility.readReturn();
            return false;
        }else {
            System.out.println("您的用户名是："+s);
            System.out.println("请牢记！");
            AccountUtility.readReturn();
            return true;
        }
    }

    private void IterCustomerList(List<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println("—————————————————————————————");
            System.out.println("    |   ID："+customer.getId());
            System.out.println("    |   用户名："+customer.getName());
            System.out.println("    |   密码："+customer.getPassword());
            System.out.println("    |   电子邮箱："+customer.getCustomer_Email());
            System.out.println("    |   手机号："+customer.getCustomer_Phone());
            System.out.println("    |   余额："+customer.getCustomer_money());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
        }
    }


}
