package edu.beihua.KarryCode.service.Impl;

import edu.beihua.KarryCode.Command_Control.CommandView;
import edu.beihua.KarryCode.DBCon.DBCon;
import edu.beihua.KarryCode.entity.Admin;
import edu.beihua.KarryCode.mapper.IAdminMapper;
import edu.beihua.KarryCode.mapper.ICustomerMapper;
import edu.beihua.KarryCode.service.*;
import edu.beihua.KarryCode.tools.AccountUtility;
import edu.beihua.KarryCode.view.PanelView;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.ResultSet;
import java.util.Scanner;

import static edu.beihua.KarryCode.Command_Control.CommandView.examine;

public class AdminServiceImpl implements IAdminService {
    Scanner scanner = new Scanner(System.in);
    IMessageService iMessageService = new MessageServiceImpl();
    DBCon dbCon = new DBCon();
    SqlSessionFactory sqlSessionFactory = dbCon.sqlSessionFactory();
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
    public Admin Login() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IAdminMapper iAdminMapper = sqlSession.getMapper(IAdminMapper.class);

        System.out.println("请输入账号：");
        String name=scanner.next();
        if (examine(name)){
            return null;
        }
        System.out.println("请输入密码：");
        String pass=scanner.next();
        if (examine(pass)){
            return null;
        }
        Admin admin = iAdminMapper.Login(name,pass);
        if (admin==null){
            System.out.println("管理员账号或密码错误！");
            AccountUtility.readReturn();
            return null;
        }else {
            System.out.println("欢迎："+admin.getName()+"超级管理员");
            //System.out.println(admin.toString());
            AccountUtility.readReturn();
            return admin;
        }
    }
    ICustomerService iCustomerService = new CustomerServiceImpl();
    IVehicleService iVehicleService = new VehicleServiceImpl();
    IOrderService iOrderService = new OrderServiceImpl();
    @Override
    public void AdminView(Admin admin) {

        if (admin==null){
            System.out.println("|抱歉你没有此权限！");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        PanelView panelView = new PanelView();

        while (true){
            panelView.panelForAdmin();
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
                continue;
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
                    System.out.println("——————————————————用户查询————————————————");
                    System.out.println("1.以用户名进行查询（模糊查询）            2.查询所有用户");
                    System.out.println("|请输入：");
                    int select3 = AccountUtility.readInt();
                    switch (select3) {
                        case 1: {
                            System.out.println("——————————————————以用户名进行查询（模糊查询）————————————————");
                            iCustomerService.queryByCustomerName_fuzzy(admin);

                            break;
                        }
                        case 2:{
                            System.out.println("——————————————————查询所有用户————————————————");
                            iCustomerService.queryAllCustomer(admin);
                            break;
                        }
                        default:{
                            System.out.println("|选项无效！");
                            break;
                        }
                    }
                    break;
                }
                case 2:{
                    System.out.println("——————————————————车辆查询————————————————");
                    System.out.println("1.以车辆名进行查询（模糊查询）            2.查询所有车辆");
                    int select3 = AccountUtility.readInt();
                    switch (select3) {
                        case 1: {
                            System.out.println("——————————————————以车辆名进行查询（模糊查询）————————————————");
                            iVehicleService.queryByVehicleName_fuzzy(admin);
                            AccountUtility.readReturn();
                            break;
                        }
                        case 2:{
                            System.out.println("——————————————————查询所有车辆————————————————");
                            iVehicleService.showAllVehicles_10in1();
                            break;
                        }
                        default:{
                            System.out.println("|选项无效！");
                            break;
                        }
                    }
                    break;
                }
                case 3:{
                    System.out.println("——————————————————指定车牌号对信息进行修改————————————————");
                    iVehicleService.vehicleInformationModifiedByLicense(admin);
                    break;
                }
                case 4:{
                    System.out.println("——————————————————指定用户名对信息进行修改————————————————");
                    iCustomerService.CustomerInformationModified(admin);
                    break;
                }
                case 5:{
                    System.out.println("——————————————————添加车辆————————————————");
                    iVehicleService.addVehicle(admin);

                    break;
                }
                case 6:{
                    System.out.println("——————————————————评论控制————————————————");
                    iMessageService.commentControl(admin);
                    break;
                }
                case 7:{
                    System.out.println("——————————————————指定车牌号，查看车辆租赁占用信息———————————————");
                    iOrderService.occupancyInformation();
                    break;
                }
                case 8:{
                    System.out.println("——————————————————退出————————————————");
                    AccountUtility.readReturn();
                    return;
                }

                default:{
                    System.out.println("|选项不存在！");
                    AccountUtility.readReturn();
                    break;
                }
            }
        }
    }

}
