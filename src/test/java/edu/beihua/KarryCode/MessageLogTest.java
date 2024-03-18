package edu.beihua.KarryCode;


import edu.beihua.KarryCode.entity.MessageLog;
import edu.beihua.KarryCode.repositoryMongo.IMessageLogRepMongo;
import edu.beihua.KarryCode.repositoryMongo.IUserRepMongo;
import edu.beihua.KarryCode.service.IMessageLogeService;
import edu.beihua.KarryCode.tools.MessageActivity;
import edu.beihua.KarryCode.tools.Permissions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/23 上午 10:14
 * @PackageName edu.beihua.KarryCode
 * @ClassName MessageLogTest
 * @Description TODO
 * @Version 1.0
 */
public class MessageLogTest extends SpringbootTest {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    IUserRepMongo iUserRepMongo;
    @Autowired
    IMessageLogRepMongo iMessageLogRepMongo;
    @Test
    public void insertMessageLog(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MessageLog lkr = MessageLog
                .builder()
                .messageActivity(MessageActivity.Publish)
                .OperationDate(simpleDateFormat.format(date))
                .Permissions(Permissions.CUSTOMER)
                .Successful(true).Visible(true)
                .Operator("test")
                .build();
        MessageLog insert = mongoTemplate.insert(lkr);
        System.out.println(insert);

    }
    @Test
    public void testPage(){
//        List<UserLogonLog> userLogonLogs = iUserRepMongo.Log_ShowPaginationUserRepMongo(0, 10);
//        for (UserLogonLog userLogonLog : userLogonLogs) {
//            System.out.println("userLogonLogs = " + userLogonLog);
//        }
        long l = iUserRepMongo.Log_ShowLogCount();
        System.out.println("l = " + l);
    }
    @Test
    public void testView(){
        List<MessageLog> messageLogs = iMessageLogRepMongo.Log_VisitorViewPagination(0, 10);
        for (MessageLog messageLog : messageLogs) {
            System.out.println("messageLog = " + messageLog);
        }
    }
    @Autowired
    IMessageLogeService iMessageLogeService;
    @Test
    public void testXX(){
        iMessageLogeService.showMessage();
    }
}
