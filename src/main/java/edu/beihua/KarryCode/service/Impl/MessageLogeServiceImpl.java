package edu.beihua.KarryCode.service.Impl;

import edu.beihua.KarryCode.entity.MessageLog;
import edu.beihua.KarryCode.entity.UserLogonLog;
import edu.beihua.KarryCode.repositoryMongo.IMessageLogRepMongo;
import edu.beihua.KarryCode.service.IMessageLogeService;
import edu.beihua.KarryCode.tools.AccountUtility;
import edu.beihua.KarryCode.tools.MessageActivity;
import edu.beihua.KarryCode.tools.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/10/8 下午 4:32
 * @PackageName edu.beihua.KarryCode.service.Impl
 * @ClassName MessageLogeServiceImpl
 * @Description TODO
 * @Version 1.0
 */
@Service
public class MessageLogeServiceImpl implements IMessageLogeService {
    @Autowired
    IMessageLogRepMongo iMessageLogRepMongo;
    @Override
    public void showMessage() {
        long l = iMessageLogRepMongo.Log_LogCount();
        int pageNumber=0;
        int pageSize=10;
        long pages = l / pageSize;
        while (true){

            System.out.println(" 序号    |              操作日期                |     是否成功     |          操作等级           |        用户名        |        操作内容   ");
            List<MessageLog> messageLogs = iMessageLogRepMongo.Log_VisitorViewPagination(pageNumber, pageSize);
            int indexNumLead = pageSize * pageNumber;
            for (MessageLog repMongo : messageLogs) {
                indexNumLead++;
                String operationDate = repMongo.getOperationDate();
                boolean successful = repMongo.isSuccessful();
                MessageActivity messageActivity = repMongo.getMessageActivity();
                String operator = repMongo.getOperator();
                Permissions permissions = repMongo.getPermissions();
                System.out.println(" "+indexNumLead+"       |        "+operationDate+"         |     "+successful+"      |       "+permissions+"                |           "+operator+"     |              "+messageActivity+"   ");


            }
            int truePage = pageNumber + 1;
            System.out.println("                                                                                                                           第 " + truePage +" 页");

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
