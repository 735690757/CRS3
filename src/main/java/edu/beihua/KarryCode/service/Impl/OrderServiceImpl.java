package edu.beihua.KarryCode.service.Impl;

import edu.beihua.KarryCode.Command_Control.CommandView;
import edu.beihua.KarryCode.DBCon.DBCon;
import edu.beihua.KarryCode.entity.Customer;
import edu.beihua.KarryCode.entity.Order;
import edu.beihua.KarryCode.entity.Vehicle;
import edu.beihua.KarryCode.mapper.ICustomerMapper;
import edu.beihua.KarryCode.mapper.IOrderMapper;
import edu.beihua.KarryCode.mapper.IVehicleMapper;
import edu.beihua.KarryCode.service.IOrderService;
import edu.beihua.KarryCode.service.IVehicleService;
import edu.beihua.KarryCode.tools.AccountUtility;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderServiceImpl implements IOrderService {
    double deposit=7000;

    IVehicleService iVehicleService = new VehicleServiceImpl();
    DBCon dbCon = new DBCon();
    SqlSessionFactory sqlSessionFactory = dbCon.sqlSessionFactory();
    Scanner scanner = new Scanner(System.in);
    public<T> boolean examine(T s){
        String ss=(String) s;
        if (new CommandView().Command(ss)==1){
            System.out.println("|控制级主动中断");
            AccountUtility.readReturn();
            return true;
        }
        return false;
    }
    @Override
    public boolean rental(Customer customer) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IVehicleMapper iVehicleMapper = sqlSession.getMapper(IVehicleMapper.class);

        IOrderMapper iOrderMapper = sqlSession.getMapper(IOrderMapper.class);

        System.out.println("————————————————————————————————————————————————请先浏览————————————————————————————————————————————————");
        iVehicleService.showAllVehicles_10in1();
        System.out.println("————————————————————————————————————————————————浏览结束————————————————————————————————————————————————");
        System.out.println("|请输入车牌号：");
        String cusWant =scanner.next();
        if (this.examine(cusWant)){
            return false;
        }
        Vehicle vehicle = iVehicleMapper.selectVehicle(cusWant);
        if (vehicle==null){
            System.out.println("车辆不存在，请检查！");
            AccountUtility.readReturn();
            return false;
        }else if (vehicle.isVehicle_state()){
            System.out.println("当前车辆已经被占用！");
            AccountUtility.readReturn();
            return false;
        }else {
            System.out.println("————————————————当前车辆可租,车辆信息如下————————————————");
            System.out.println("|名称："+vehicle.getVehicle_name());
            System.out.println("|车牌号："+vehicle.getVehicle_license());
            System.out.println("|租金:"+vehicle.getVehicle_rent()+" 元/月");
            System.out.println("|押金："+deposit);

            int cusWantDays;
            while (true){
                System.out.println("|请输入你要租的月数(最小月数：3月)：");
                cusWantDays = AccountUtility.readInt();
                if (cusWantDays<3){
                    System.out.println("|出借月数过短，请重新输入！");
                }else {
                    System.out.println("|我看行！");
                    break;
                }
            }
            //金额保证
            double sum = deposit + cusWantDays * vehicle.getVehicle_rent();
            if (sum>customer.getCustomer_money()){
                System.out.println("\033[31m" +
                        "\n" +
                        "  __       _ _                \n" +
                        " / _| __ _(_) |_   _ _ __ ___ \n" +
                        "| |_ / _` | | | | | | '__/ _ \\\n" +
                        "|  _| (_| | | | |_| | | |  __/\n" +
                        "|_|  \\__,_|_|_|\\__,_|_|  \\___|\n" +
                        "                              \n"+
                        "\033[0m");
                System.out.println("|但！抱歉，你现在只有"+customer.getCustomer_money()+"元，而租赁"+vehicle.getVehicle_name()+"汽车"+cusWantDays+"个月需要"+sum+"元");
                System.out.println("|请充值！");
                AccountUtility.readReturn();
                return false;
            }
            double Final_balance=customer.getCustomer_money()-sum;
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("余额够抵！租赁信息如下：" );
            System.out.println("    |   名称："+vehicle.getVehicle_name());
            System.out.println("    |   车牌号："+vehicle.getVehicle_license());
            System.out.println("    |   总租金："+sum+" 元");
            System.out.println("    |   押金："+deposit+" 元");
            System.out.println("    |   扣前余额："+customer.getCustomer_money()+" 元");
            System.out.println("    |   扣后余额："+Final_balance+" 元");
            System.out.println("    |   租时："+cusWantDays+"个月");
            System.out.println("    |   租赁日期："+simpleDateFormat.format(date));
            if (AccountUtility.readConfirmSelection()=='N'){
                System.out.println("好的那您再看看呗~~");
                AccountUtility.readReturn();
                return false;
            }else{
                System.out.println("————————————————租赁行为正式开始————————————————");
                Order order = new Order();
                order.setCustomer_name(customer.getName());
                order.setVehicle_license(vehicle.getVehicle_license());
                order.setOrder_borrowdate(simpleDateFormat.format(date));
                order.setOrder_fee(sum);
                int con = iOrderMapper.insertOrder(order);
                if (con>0){
                    int con2 = iVehicleMapper.updateVehicleState(order.getVehicle_license(), true);
                    if (con2>0){
                        System.out.println("|租赁成功！！");
                        System.out.println("\n" +
                                "\033[92m                                 \n" +
                                " ___ _   _  ___ ___ ___  ___ ___ \n" +
                                "/ __| | | |/ __/ __/ _ \\/ __/ __|\n" +
                                "\\__ \\ |_| | (_| (_|  __/\\__ \\__ \\\n" +
                                "|___/\\__,_|\\___\\___\\___||___/___/\n" +
                                "                                 \033[0m\n");
                        sqlSession.commit();
                        AccountUtility.readReturn();
                        return true;
                    }else{
                        System.out.println("|车辆状态更改失败，租赁中断");
                        sqlSession.rollback();
                        AccountUtility.readReturn();
                        return false;
                    }
                }else {
                    System.out.println("|订单更新失败，租赁中断");
                    sqlSession.rollback();
                    AccountUtility.readReturn();
                    return false;

                }
            }
        }
    }

    @Override
    public void selectOrder(Customer customer, int sel) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IVehicleMapper iVehicleMapper = sqlSession.getMapper(IVehicleMapper.class);

        IOrderMapper iOrderMapper = sqlSession.getMapper(IOrderMapper.class);

        //1. 查询所有订单记录     2. 查询已归还的订单记录    3. 查询仍未归还的订单记录
        Boolean cha = null;
        switch (sel){
            case 1:{
                System.out.println("————————————————查询所有订单记录————————————————");
                break;
            }
            case 2:{
                System.out.println("————————————————查询已归还订单记录————————————————");
                cha=true;
                break;
            }
            case 3:{
                System.out.println("————————————————查询未归还订单记录————————————————");
                cha=false;
                break;
            }
            default:{
                System.out.println("选项不正确！");
                return;
            }
        }
        int count=0;
        List<Order> orders = iOrderMapper.selectOrder(customer, cha);
        for (Order order : orders) {
            System.out.println("—————————————————————————————————————————");
            System.out.println("❍    |   第 "+ ++count + " 辆车");
            System.out.println("❍    |   用户名："+order.getCustomer_name());
            Vehicle vehicle = iVehicleMapper.selectVehicle(order.getVehicle_license());
            System.out.println("❍    |   车辆名："+vehicle.getVehicle_name());
            System.out.println("❍    |   车牌号："+vehicle.getVehicle_license());
            System.out.println("❍    |   租赁日期："+order.getOrder_borrowdate());
            if (order.getOrder_returndate()==null){
                System.out.println("❍    |   归还日期：咱也不知道，咱也不敢问");
            }else {
                System.out.println("❍    |   归还日期："+order.getOrder_returndate());
            }
            System.out.println("❍    |   租赁费："+order.getOrder_fee());
            if (order.isOrder_return()){
                System.out.println("❍    |   归还状态：已归还");
            }else{
                System.out.println("❍    |   归还状态：未归还");

            }
        }
        cha=null;
        AccountUtility.readReturn();
    }

    @Override
    public boolean returnVehicle(Customer customer) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IOrderMapper iOrderMapper = sqlSession.getMapper(IOrderMapper.class);
        IVehicleMapper iVehicleMapper = sqlSession.getMapper(IVehicleMapper.class);

        ICustomerMapper iCustomerMapper = sqlSession.getMapper(ICustomerMapper.class);

        /**
         * 1.展示一下用户没有还的车
         * 2.合法性检查
         *      2.1确定是否被占用
         *      2.2确定是否被该用户占用
         * 3.归还行为
         *      3.1 展示订单
         *      3.2 设置订单归还日期
         *      3.3 设置订单归还状态
         *      3.4 设置车辆占用状态
         */
        List<Order> orders = iOrderMapper.selectOrder(customer, false);
        int count =  0;
        if (orders.size()==0){
            System.out.println("您已经完成全部车辆的归还");
            AccountUtility.readReturn();
            return true;
        }else{
            for (Order order : orders) {
                System.out.println("—————————————————————请检查————————————————————");

                System.out.println("❍    |   第 "+ ++count + " 辆车");
                Vehicle vehicle = iVehicleMapper.selectVehicle(order.getVehicle_license());
                System.out.println("❍    |   车辆名："+vehicle.getVehicle_name());
                System.out.println("❍    |   车牌号："+vehicle.getVehicle_license());
                System.out.println("❍    |   租赁日期："+order.getOrder_borrowdate());
                System.out.println("❍    |   费用："+order.getOrder_fee()+"包括押金："+deposit+"元");
            }
            System.out.println("————————————————————————————————————————————");
            System.out.println("|想还哪辆车呢？");
            System.out.println("|请输入车牌号：");
            String license = scanner.next();
            //确定是否被占用
            Vehicle vehicle = iVehicleMapper.selectVehicle(license);
            if (vehicle==null){
                System.out.println("输入有误！车辆不存在！！");
                AccountUtility.readReturn();
                return false;
            }else {
                if (vehicle.isVehicle_state()){
                    //被占用
                    //确定是否被该用户占用
                    Order order = iOrderMapper.selectOrder_ByLNR(license, customer.getName(), false);
                    if (order==null){
                        System.out.println("你没有租这辆车，你无法替别人归还");
                        AccountUtility.readReturn();

                        return false;
                    }else {
                        // 展示订单
                        System.out.println("————————————————————————————————————");
                        System.out.println("|订单ID："+order.getOrder_id());
                        System.out.println("|用户名："+order.getCustomer_name());
                        System.out.println("|车牌号："+order.getVehicle_license());
                        System.out.println("|借车时间："+order.getOrder_borrowdate());
                        System.out.println("|费用："+order.getOrder_fee());
                        System.out.println("————————————————请检查———————————————");
                        if (AccountUtility.readConfirmSelection()=='N'){
                            System.out.println("|好的那您再看看呗~~");
                            AccountUtility.readReturn();
                            return false;
                        }else {
                            System.out.println("|收到，正在执行归还！");
                            // 设置订单归还日期
                            // 设置订单归还状态
                            Date date = new Date();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            try{
                                iOrderMapper.updateOrderReturnDateAndSta_ByOrderId(simpleDateFormat.format(date),order.getOrder_id());
                                // 设置车辆占用状态
                                iVehicleMapper.updateVehicleState(license,false);

                                customer.setCustomer_money(deposit+customer.getCustomer_money());
                                iCustomerMapper.updateMoneyBy_CName(customer.getName(),customer.getCustomer_money());
                                sqlSession.commit();
                                sqlSession.clearCache();
                                System.out.println("押金"+deposit+"元已归还！");
                                System.out.println("\n" +
                                        "\033[92m                                 \n" +
                                        " ___ _   _  ___ ___ ___  ___ ___ \n" +
                                        "/ __| | | |/ __/ __/ _ \\/ __/ __|\n" +
                                        "\\__ \\ |_| | (_| (_|  __/\\__ \\__ \\\n" +
                                        "|___/\\__,_|\\___\\___\\___||___/___/\n" +
                                        "                                 \033[0m\n");
                                System.out.println("车辆归还成功！！");
                                AccountUtility.readReturn();

                                return true;
                            } catch (Exception e) {
                                System.out.println("更新异常");
                                sqlSession.rollback();
                                AccountUtility.readReturn();

                                return false;
                            }
                        }
                    }
                }else {
                    //木有占用
                    System.out.println("输入有误！车辆未被占用！！");
                    return false;

                }
            }

        }

    }

    @Override
    public boolean occupancyInformation() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IVehicleMapper iVehicleMapper = sqlSession.getMapper(IVehicleMapper.class);
        IOrderMapper iOrderMapper = sqlSession.getMapper(IOrderMapper.class);
        System.out.println("请输入车牌号：");
        String Vehicle_license = scanner.next();
        Vehicle vehicle = iVehicleMapper.selectVehicle(Vehicle_license);
        if (vehicle==null){
            System.out.println("车辆不存在，请检查！");
            return false;
        }
        if (vehicle.isVehicle_state()){

            System.out.println("————————————————————车辆占用————————————————————");
            Order order = iOrderMapper.selectOrder_Customer(vehicle.getVehicle_license());
            Vehicle vehicle1 = order.getVehicle();
            System.out.println("————————————————————————————————————————————————");
            System.out.println("车辆信息：");
            System.out.println("ID："+order.getOrder_id());
            System.out.println("车牌号："+order.getVehicle_license());
            System.out.println("车名："+vehicle1.getVehicle_name());
            System.out.println("租金："+vehicle1.getVehicle_rent());
            System.out.println("————————————————————————————————————————————————");
            System.out.println("占用者信息：");
            Customer customer = order.getCustomer();
            System.out.println("ID："+customer.getId());
            System.out.println("用户名："+customer.getName());
            System.out.println("密码:"+customer.getPassword());
            System.out.println("手机号："+customer.getCustomer_Phone());
            System.out.println("Email："+customer.getCustomer_Email());
            System.out.println("余额："+customer.getCustomer_money());
            System.out.println("————————————————————————————————————————————————");
        }else {
            System.out.println("空闲");

        }

        AccountUtility.readReturn();
        return false;
    }
}
