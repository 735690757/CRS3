package edu.beihua.KarryCode.service.Impl;

import edu.beihua.KarryCode.DBCon.DBCon;
import edu.beihua.KarryCode.entity.Admin;
import edu.beihua.KarryCode.entity.Vehicle;
import edu.beihua.KarryCode.mapper.ICustomerMapper;
import edu.beihua.KarryCode.mapper.IVehicleMapper;
import edu.beihua.KarryCode.service.IVehicleService;
import edu.beihua.KarryCode.tools.AccountUtility;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class VehicleServiceImpl implements IVehicleService {

    DBCon dbCon = new DBCon();
    SqlSessionFactory sqlSessionFactory = dbCon.sqlSessionFactory();

    @Override
    public void showAllVehicles_10in1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IVehicleMapper iVehicleMapper = sqlSession.getMapper(IVehicleMapper.class);

        int VeNum = iVehicleMapper.selectVehiclesCount();
        //System.out.println("车牌号             |       "+" 车名                  |        "+"租金（元/天）          |       "+"车辆状态（0：可租，1：占用）");

        int nowPage = 0;
        int pages = 1;
        while (true) {
            sqlSession.clearCache();
            sqlSession.commit();
            List<Vehicle> vehicles = iVehicleMapper.selectLimit10(nowPage, 10);
            String sta;
            System.out.println("车牌号             |       " + " 车名                  |        " + "租金（元/天）                 |       " + "车辆状态");
            for (Vehicle vehicle : vehicles) {
                if (vehicle.isVehicle_state()){
                    sta="状态：占用中";
                }else{
                    sta="状态：空闲中";

                }
                System.out.println(vehicle.getVehicle_license()+
                        "             |       "+vehicle.getVehicle_name()+
                        "              |           "+vehicle.getVehicle_rent()+
                        "                  |               "+sta);
                System.out.println();

            }
            int sumPages=(int)(VeNum/10)+1;
            System.out.println("第"+pages+"页"+"，共"+sumPages+"页");
            System.out.println("1:上一页       2：下一页       3：退出"  );
            int userSelect = AccountUtility.readInt();
            switch (userSelect){
                case 1:{
                    if (nowPage==0){
                        System.out.println("上不了一点");
                        AccountUtility.readReturn();
                    }else {
                        nowPage-=10;
                        pages--;
                    }
                    break;
                }
                case 2:{
                    if (pages>=sumPages){
                        System.out.println("下不了一点");
                        AccountUtility.readReturn();
                    }else {
                        nowPage+=10;
                        pages++;
                    }
                    break;
                }
                case 3:{
                    return;
                }
                default:{
                    System.out.println("|没有这个选项！");
                    AccountUtility.readReturn();
                    break;
                }
            }
        }
    }
Scanner scanner = new Scanner(System.in);
    @Override
    public void queryByVehicleName_fuzzy(Admin admin) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IVehicleMapper iVehicleMapper = sqlSession.getMapper(IVehicleMapper.class);

        if (admin==null){
            System.out.println("管理员未登录！");
            return ;
        }
        System.out.println("请输入车辆名：");
        String VName = scanner.next();
        List<Vehicle> vehicles = iVehicleMapper.queryByVehicleName_fuzzy(VName);
        if (vehicles.size()==0){

            System.out.println("没找到");
            AccountUtility.readReturn();
        }else {
            for (Vehicle vehicle : vehicles) {
                System.out.println("————————————————————————————————————————");
                System.out.println("车辆ID："+vehicle.getVehicle_id());
                System.out.println("车牌号："+vehicle.getVehicle_license());
                System.out.println("车辆名称："+vehicle.getVehicle_name());
                System.out.println("租金（元/月）："+vehicle.getVehicle_rent());
                if (vehicle.isVehicle_state()){
                    System.out.println("状态：占用中");
                }else{
                    System.out.println("状态：空闲中");

                }
            }
        }
    }

    @Override
    public boolean vehicleInformationModifiedByLicense(Admin admin) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IVehicleMapper iVehicleMapper = sqlSession.getMapper(IVehicleMapper.class);

        if (admin==null) {
            System.out.println("管理员未登录！");
            return false;
        }
        System.out.println("请输入车牌号，然后根据系统提示进行修改：");
        String adminWantTo=scanner.next();
        Vehicle vehicle = iVehicleMapper.selectVehicle(adminWantTo);
        int adminSelect;
        if (vehicle==null){
            System.out.println("\033[31m" +
                    "\n" +
                    "  __       _ _                \n" +
                    " / _| __ _(_) |_   _ _ __ ___ \n" +
                    "| |_ / _` | | | | | | '__/ _ \\\n" +
                    "|  _| (_| | | | |_| | | |  __/\n" +
                    "|_|  \\__,_|_|_|\\__,_|_|  \\___|\n" +
                    "                              \n"+
                    "\033[0m");
            System.out.println("输入有误，车辆不存在！");
            AccountUtility.readReturn();
            return false;
        }else {
            System.out.println("————————————————————————————————————————");
            System.out.println("车辆ID："+vehicle.getVehicle_id());
            System.out.println("车牌号："+vehicle.getVehicle_license());
            System.out.println("车辆名称："+vehicle.getVehicle_name());
            System.out.println("租金（元/月）："+vehicle.getVehicle_rent());
            if (vehicle.isVehicle_state()){
                System.out.println("状态：占用中");
            }else{
                System.out.println("状态：空闲中");

            }
            boolean cyclicStatus=false;
            boolean licenseModification=false;
            String licenseBackup=vehicle.getVehicle_license();
            while (true) {
                if (cyclicStatus) {
                    break;
                }
                System.out.println("————————————————————————————————————");
                System.out.println("1. 修改车牌号    2. 修改车辆名    3. 修改租金     4. 退出修改     5. 提交修改");
                System.out.println("请输入修改项：");
                adminSelect = AccountUtility.readIntU(7);

                switch (adminSelect){
                    case 1:{
                        System.out.println("—————修改车牌号—————");
                        String newLicense;
                        System.out.println(" 输入新的车牌号：");
                        newLicense = scanner.next();
                        System.out.println("旧的车牌号："+vehicle.getVehicle_license());
                        System.out.println("新的车牌号："+newLicense);
                        System.out.println("确认吗？");
                        char c = AccountUtility.readConfirmSelection();
                        if (c=='Y'){
                            vehicle.setVehicle_license(newLicense);
                            System.out.println("完成！");
                            licenseModification = true;
                            break;
                        }else {
                            System.out.println("好的，这将不保存修改！");
                        }
                        AccountUtility.readReturn();
                        break;
                    }
                    case 2:{
                        System.out.println("—————修改车辆名—————");
                        String newName;
                        System.out.println("请输入新车名：");
                        newName = scanner.next();
                        System.out.println("旧的车名："+vehicle.getVehicle_name());
                        System.out.println("新的车名："+newName);
                        System.out.println("确认吗？");
                        char c = AccountUtility.readConfirmSelection();
                        if (c=='Y'){
                            vehicle.setVehicle_name(newName);
                            System.out.println("完成！");
                            break;
                        }else {
                            System.out.println("好的，这将不保存修改！");
                        }
                        AccountUtility.readReturn();
                        break;
                    }
                    case 3:{
                        System.out.println("—————修改租金—————");
                        double newRent = 0;
                        System.out.println(" 输入新的租金：");
                        newRent = scanner.nextDouble();
                        System.out.println("旧的租金："+vehicle.getVehicle_rent());
                        System.out.println("新的租金："+newRent);
                        System.out.println("确认吗？");
                        char c = AccountUtility.readConfirmSelection();
                        if (c=='Y'){
                            vehicle.setVehicle_rent(newRent);
                            System.out.println("完成！");
                            break;
                        }else {
                            System.out.println("好的，这将不保存修改！");
                        }
                        AccountUtility.readReturn();
                        break;
                    }
                    case 4:{
                        System.out.println("退出");
                        AccountUtility.readReturn();
                        cyclicStatus=true;
                        break;
                    }
                    case 5:{
                        System.out.println("—————提交修改—————");
                        if (licenseModification) {
                            System.out.println("请注意，系统检测到你修改已修改车牌号，这一行为将会引起部分用户订单内容级联改变！！");
                            System.out.println("您确定吗？");
                            if (AccountUtility.readConfirmSelection() == 'Y') {
                                System.out.println("好的");
                            } else {
                                vehicle.setVehicle_license(licenseBackup);
                                System.out.println("反悔成功！");
                            }
                        }
                        AccountUtility.readReturn();
                        System.out.println("    |车牌号："+vehicle.getVehicle_license());
                        System.out.println("    |车辆名："+vehicle.getVehicle_name());
                        System.out.println("    |租金："+vehicle.getVehicle_rent());
                        if (vehicle.isVehicle_state()){
                            System.out.println("    |状态：占用中");
                        }else
                            System.out.println("    |状态：空闲");
                        System.out.println("确认提交修改吗？");
                        if (AccountUtility.readConfirmSelection()=='Y'){
                        System.out.println("好的");
                        try {
                            int i = iVehicleMapper.vehicleInformationModifiedAll(
                                    vehicle.getVehicle_license(),
                                    vehicle.getVehicle_name(),
                                    vehicle.getVehicle_rent(),
                                    vehicle.isVehicle_state(),
                                    licenseBackup
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
                                System.out.println("上传成功！");
                                AccountUtility.readReturn();
                                System.out.println("更新完成！！");
                                return true;
                            }
                        } catch (Exception e) {
                            sqlSession.rollback();
                            System.out.println("更新异常");
                            System.out.println("\033[31m" +
                                    "\n" +
                                    "  __       _ _                \n" +
                                    " / _| __ _(_) |_   _ _ __ ___ \n" +
                                    "| |_ / _` | | | | | | '__/ _ \\\n" +
                                    "|  _| (_| | | | |_| | | |  __/\n" +
                                    "|_|  \\__,_|_|_|\\__,_|_|  \\___|\n" +
                                    "                              \n"+
                                    "\033[0m");
                            System.out.println("库中以经有车牌号为："+vehicle.getVehicle_license()+"的车了，无法更新哦！");
                            System.out.println("更新失败！！");

                        }
                        AccountUtility.readReturn();
                        return true;
                    }
                }
                default:{
                    System.out.println("不存在的选项");
                    break;
                    }

                }
            }
            return false;
        }
    }

    @Override
    public boolean addVehicle(Admin admin) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IVehicleMapper iVehicleMapper = sqlSession.getMapper(IVehicleMapper.class);
        if (admin==null){
            System.out.println("|管理员未登录！！");
            return false;
        }
        String Vehicle_license;
        String Vehicle_name;
        double Vehicle_rent;
        while (true){
            System.out.println("请输入车牌号：");
            Vehicle_license = scanner.next();
            Vehicle vehicle = iVehicleMapper.selectVehicle(Vehicle_license);
            if (vehicle!=null){
                System.out.println("\033[31m" +
                        "\n" +
                        "  __       _ _                \n" +
                        " / _| __ _(_) |_   _ _ __ ___ \n" +
                        "| |_ / _` | | | | | | '__/ _ \\\n" +
                        "|  _| (_| | | | |_| | | |  __/\n" +
                        "|_|  \\__,_|_|_|\\__,_|_|  \\___|\n" +
                        "                              \n"+
                        "\033[0m");
                System.out.println("这个车牌号已经存在！");
                AccountUtility.readReturn();
                return false;
            }else {
                break;
            }
        }
        System.out.println("请输入车辆名：");
        Vehicle_name = scanner.next();

        while (true){
            try {
                System.out.println("请输入租金（元/月）：");
                Vehicle_rent = AccountUtility.parseDouble();
                break;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("新车的情况：");
        System.out.println("——————————————————————————————————————");
        System.out.println("车牌号："+Vehicle_license);
        System.out.println("车名："+Vehicle_name);
        System.out.println("租金（元/月）："+Vehicle_rent);
        System.out.println("——————————————————————————————————————");
        System.out.println("确认提交吗？");
        if (AccountUtility.readConfirmSelection()=='Y'){
            System.out.println("好的");
            try {
                int i = iVehicleMapper.addVehicle(Vehicle_license, Vehicle_name, Vehicle_rent);
                if (i>0){
                    System.out.println("\n" +
                            "\033[92m                                 \n" +
                            " ___ _   _  ___ ___ ___  ___ ___ \n" +
                            "/ __| | | |/ __/ __/ _ \\/ __/ __|\n" +
                            "\\__ \\ |_| | (_| (_|  __/\\__ \\__ \\\n" +
                            "|___/\\__,_|\\___\\___\\___||___/___/\n" +
                            "                                 \033[0m\n");
                    System.out.println("添加成功！！");
                    sqlSession.commit();
                    AccountUtility.readReturn();
                }else {
                    System.out.println("\033[31m" +
                            "\n" +
                            "  __       _ _                \n" +
                            " / _| __ _(_) |_   _ _ __ ___ \n" +
                            "| |_ / _` | | | | | | '__/ _ \\\n" +
                            "|  _| (_| | | | |_| | | |  __/\n" +
                            "|_|  \\__,_|_|_|\\__,_|_|  \\___|\n" +
                            "                              \n"+
                            "\033[0m");
                    System.out.println("失败");
                    AccountUtility.readReturn();
                }
            } catch (Exception e) {
                System.out.println(e.getCause());
                sqlSession.rollback();

            }
        }
        return false;
    }
}
