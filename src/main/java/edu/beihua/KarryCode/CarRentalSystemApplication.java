package edu.beihua.KarryCode;


import edu.beihua.KarryCode.OtherThread.FlushedThread;
import edu.beihua.KarryCode.OtherThread.IPThread;
import edu.beihua.KarryCode.entity.Admin;
import edu.beihua.KarryCode.entity.Customer;
import edu.beihua.KarryCode.repositoryMongo.IUserRepMongo;
import edu.beihua.KarryCode.service.*;
import edu.beihua.KarryCode.service.Impl.CarRental3;
import edu.beihua.KarryCode.tools.AccountUtility;
import edu.beihua.KarryCode.view.PanelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

import static edu.beihua.KarryCode.Command_Control.CommandView.examine;
@SpringBootApplication
public class CarRentalSystemApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CarRentalSystemApplication.class, args);
        CarRentalSystemApplication CarRentalSystem = context.getBean(CarRentalSystemApplication.class);

        CarRentalSystem.CarRentalSystem3();
    }
    @Autowired
    ISystemStartupServiceMongo iSystemStartupServiceMongo;
    @Autowired
    ICustomerService iCustomerService;
    @Autowired
    IVehicleService iVehicleService;
    @Autowired
    IMessageService iMessageService;
    @Autowired
    IOrderService iOrderService;
    @Autowired
    IAdminService iAdminService;
    @Autowired
    IUserRepMongo iUserRepMongo;
    @Autowired
    FlushedThread flushedThread;
    @Autowired
    CarRental3 carRental3;
    @Autowired
    PanelView panelView;
    @Autowired
    IPThread ipThread;
    public void CarRentalSystem3() {
        ipThread.start();
        flushedThread.start();
//
        Customer Acc_Customer = null;
        Admin Acc_Admin = null;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            panelView.panel();
            String select = scanner.next();
            if (select.matches("-?\\d+(\\.\\d+)?")) {
                /*
                "-" 表示可选的负号，即数字可以是负数。
                "\d+" 表示至少一个数字。
                "(\.\d+)?" 表示小数点及其后面的数字是可选的，即数字可以是整数或小数。
                因此，这个正则表达式可以匹配的数字格式包括整数、带小数点的实数、以及带正负号的这两种类型的数。
                如果字符串符合这个格式，那么它就会返回 true。
                * */
                System.out.println("等待系统响应");
            } else {
                examine(select);
                continue;
            }
            int select2;
            try {
                select2 = Integer.parseInt(select);
            } catch (NumberFormatException e) {
                select2 = 0;
                System.out.println("不带你这么玩的奥！");
            }
            switch (select2){
                case 1:{
                    System.out.println("————————————————用户注册————————————————");
                    if (Acc_Customer!=null||Acc_Admin!=null){
                        System.out.println("用户或管理员已登录，请退出登录！");
                        AccountUtility.readReturn();
                        break;
                    }
                    iCustomerService.Register();
                    AccountUtility.readReturn();
                    break;
                }
                case 2:{
                    if (Acc_Customer!=null||Acc_Admin!=null){
                        System.out.println("用户或管理员已登录，请退出登录！");
                        AccountUtility.readReturn();
                        break;
                    }
                    Acc_Customer = iCustomerService.Login();
                    if (Acc_Customer!=null){
                        System.out.println("登陆成功！");
                        AccountUtility.readReturn();
                    }else {
                        System.out.println("登陆失败！");
                        AccountUtility.readReturn();
                    }
                    break;
                }
                case 3:{
                    if (Acc_Customer==null){
                        System.out.println("请登录！");
                        AccountUtility.readReturn();
                        break;
                    }
                    iCustomerService.howMuchMoneyDoIHave(Acc_Customer);
                    break;
                }
                case 4:{
                    System.out.println("————————————————退出登录————————————————");
                    if (Acc_Customer==null){
                        System.out.println("请登录！");
                    }else {
                        System.out.println("不留天涯苦,何处不相逢?\n                     "+Acc_Customer.getName()+"  再见");
                        Acc_Customer=null;
                    }

                    AccountUtility.readReturn();
                    break;
                }
                case 5:{
                    System.out.println("————————————————我要充钱/取钱————————————————");
                    if (Acc_Customer==null){
                        System.out.println("请登录！");
                        AccountUtility.readReturn();
                        break;
                    }
                    System.out.println("1.充钱            2.取钱");
                    int num = scanner.nextInt();
                    switch (num){
                        case 1:{
                            System.out.println("————————————————充钱————————————————");
                            iCustomerService.chargeOrRecharge(Acc_Customer,num);
                            break;
                        }
                        case 2:{
                            System.out.println("————————————————取钱————————————————");
                            iCustomerService.chargeOrRecharge(Acc_Customer,num);
                            break;
                        }
                        default:{
                            System.out.println("没有这个选项！！");
                            break;
                        }

                    }
                    break;
                }
                case 6:{
                    System.out.println("————————————让我看看都有什么车————————————");
                    iVehicleService.showAllVehicles_10in1();
                    AccountUtility.readReturn();
                    break;
                }
                case 7:{
                    System.out.println("————————————————————————————————————————————————我要租车————————————————————————————————————————————————");
                    if(Acc_Customer==null){
                        System.out.println("当前未登录！");
                        AccountUtility.readReturn();
                        break;
                    }
                    iOrderService.rental(Acc_Customer);

                    break;

                }
                case 8:{
                    System.out.println("—————————————————————————————————————————————————");
                    System.out.println("———————————————————我租什么车了？———————————————————");
                    if(Acc_Customer==null) {
                        System.out.println("当前未登录！");
                        AccountUtility.readReturn();
                        break;
                    }
                    System.out.println("1. 查询所有订单记录     2. 查询已归还的订单记录    3. 查询仍未归还的订单记录");
                    int selectX = AccountUtility.readIntU(0);
                    iOrderService.selectOrder(Acc_Customer,selectX);
                    break;
                }
                case 9:{
                    System.out.println("———————————————————还车还车！！———————————————————");
                    if(Acc_Customer==null) {
                        System.out.println("当前未登录！");
                        AccountUtility.readReturn();
                        break;
                    }
                    iOrderService.returnVehicle(Acc_Customer);
                    break;
                }
                case 10:{
                    System.out.println("———————————————————管理员登录———————————————————");
                    if(Acc_Customer!=null) {
                        System.out.println("请退出用户登录");
                        AccountUtility.readReturn();
                        break;
                    }
                    if(Acc_Admin!=null) {
                        System.out.println("请退出管理员登录");
                        AccountUtility.readReturn();
                        break;
                    }
                    Acc_Admin = iAdminService.Login();
                    break;
                }
                case 11:{
                    System.out.println("———————————————————管理员退出———————————————————");

                    if(Acc_Admin==null) {
                        System.out.println("当前无管理员登录，无需退出");
                        AccountUtility.readReturn();
                        break;
                    }
                    Acc_Admin=null;
                    System.out.println("不留天涯苦,何处不相逢?\n                       再见");
                    AccountUtility.readReturn();
                    break;
                }
                case 12:{
                    System.out.println("———————————————————进入管理员视图———————————————————");
                    if(Acc_Admin==null) {
                        System.out.println("请先登录管理员");
                        AccountUtility.readReturn();
                        break;
                    }
                    iAdminService.AdminView(Acc_Admin);
                    break;
                }
                case 13:{
                    System.out.println("———————————————————查看留言———————————————————");
                    iMessageService.showAllMessage();
                    break;
                }
                case 14:{
                    System.out.println("———————————————————我有话说———————————————————");
                    if(Acc_Customer==null){
                        System.out.println("用户未登录！");
                        AccountUtility.readReturn();
                        break;
                    }
                    iMessageService.somethingToSay(Acc_Customer);
                    AccountUtility.readReturn();
                    break;
                }
                case 15:{
                    System.out.println("———————————————————忘记密码（用户级别）———————————————————");
                    if(Acc_Admin!=null){
                        System.out.println("您可以进入管理员界面，指定用户直接修改密码");
                        AccountUtility.readReturn();
                        break;
                    }
                    iCustomerService.forgotPassword();
                    break;
                }
                case 16:{
                    System.out.println("———————————————————忘记用户名（用户级别）———————————————————");
                    if(Acc_Admin!=null){
                        System.out.println("您可以进入管理员界面，指定用户直接修改密码");
                        AccountUtility.readReturn();
                        break;
                    }
                    iCustomerService.forgotUsername();
                    break;
                }
                case 17:{
                    System.out.println("——————————————————— < CRS3 新特性 > ———————————————————");
                    carRental3.CoreView3();
                    break;
                }
                case 18:{
                    flushedThread.stopThread();
                    System.out.println("再见！！");
                    return;
                }
                default:{
                    break;
                }
            }
        }

    }
}