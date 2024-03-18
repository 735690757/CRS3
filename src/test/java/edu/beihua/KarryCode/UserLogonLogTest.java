package edu.beihua.KarryCode;

import com.mongodb.client.MongoDatabase;
import edu.beihua.KarryCode.entity.UserLogonLog;
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
 * @Date 2023/9/17 下午 4:15
 * @PackageName edu.beihua.KarryCode
 * @ClassName UserLogonLogTest
 * @Description TODO
 * @Version 1.0
 */
public class UserLogonLogTest extends SpringbootTest {
    @Autowired
    MongoTemplate mongoTemplate;
    @Test
    public void insertUserLogonLog(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Customer lkr = new Customer(1, "lkr", "123456", "123134", "134134", 12);
        UserLogonLog build = UserLogonLog.builder().logonUserName("lkr").Successful(true).date(simpleDateFormat.format(date)).Permissions(Permissions.CUSTOMER).Visible(true).build();
        mongoTemplate.insert(build);
    }
    @Test
    public void Conn(){
        MongoDatabase db = mongoTemplate.getDb();
        System.out.println(db.getName());
    }
    @Test
    public void ToEntity(){
        List<UserLogonLog> all = mongoTemplate.findAll(UserLogonLog.class);
        for (UserLogonLog userLogonLog : all) {
            System.out.println(userLogonLog.getLogonUserName());
            System.out.println(userLogonLog.getDate());
            System.out.println(userLogonLog.getPermissions());

        }
    }
}
