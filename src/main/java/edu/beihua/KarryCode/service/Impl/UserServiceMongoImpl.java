package edu.beihua.KarryCode.service.Impl;

import edu.beihua.KarryCode.entity.UserLogonLog;
import edu.beihua.KarryCode.repositoryMongo.IUserRepMongo;
import edu.beihua.KarryCode.service.IUserServiceMongo;
import edu.beihua.KarryCode.tools.AccountUtility;
import edu.beihua.KarryCode.tools.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/17 下午 7:28
 * @PackageName edu.beihua.KarryCode.service.Impl
 * @ClassName IUserServiceMongoImpl
 * @Description TODO
 * @Version 1.0
 */
@Service
public class UserServiceMongoImpl implements IUserServiceMongo {
    @Autowired
    IUserRepMongo iUserRepMongo;
    @Override
    public void ShowAllUserRepMongo() {
        List<UserLogonLog> userRepMongo = iUserRepMongo.Log_ShowAllUserRepMongo();
        System.out.println("    用户名   |          操作日期          |     是否成功      |       可见状态");
//        Integer count=0;
        for (UserLogonLog repMongo : userRepMongo) {

//            System.out.println(repMongo);
            String logonUserName =  repMongo.getLogonUserName();
            String date = repMongo.getDate();
            boolean successful = repMongo.isSuccessful();
            Boolean visible = repMongo.getVisible();
            System.out.println("    "+logonUserName+"   |     "+date+"    |     "+successful+"      |       "+visible+"");

        }
        AccountUtility.readReturn();
    }

    @Override
    public void PaginationUserRepMongo() {
        long l = iUserRepMongo.Log_ShowLogCount();
        int pageNumber=0;
        int pageSize=10;
        long pages = l / pageSize;
        while (true){

            System.out.println("         用户名   |        操作日期         |     是否成功      |       可见状态        |        操作权限");
            List<UserLogonLog> userLogonLogs = iUserRepMongo.Log_ShowPaginationUserRepMongo(pageNumber, pageSize);
            int indexNumLead = pageSize * pageNumber;
            for (UserLogonLog repMongo : userLogonLogs) {
                indexNumLead++;
                String logonUserName =  repMongo.getLogonUserName();
                String date = repMongo.getDate();
                boolean successful = repMongo.isSuccessful();
                Boolean visible = repMongo.getVisible();
                Permissions permissions = repMongo.getPermissions();
                System.out.println(indexNumLead +"      |    "+logonUserName+"   |     "+date+"    |     "+successful+"      |       "+visible+"        |        "+permissions);

            }
            int truePage = pageNumber + 1;
            System.out.println("                                                                   第 " + truePage +" 页");

            System.out.println("1.上一页       2.下一页       3.退出");
            int i = AccountUtility.readInt();
            switch (i){
                case 2: {
//                    System.out.println("2222222222222");
                    if (pageNumber+1>pages){
                        System.out.println("到头er了！");
                        AccountUtility.readReturn();
                    }else {
                        pageNumber++;
                    }
                    break;
                }
                case 1: {
                    if (pageNumber-1<0){
                        System.out.println("到头er了！");
                        AccountUtility.readReturn();
                    }else {
                        pageNumber--;
                    }
                    break;

                }
                case 3: {
                    return;
                }
                default: {
                    System.out.println("这也不对啊？！");
                    break;
                }
            }
        }

    }
}
