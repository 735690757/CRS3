package edu.beihua.KarryCode.repositoryMongo.impl;

import edu.beihua.KarryCode.entity.User;
import edu.beihua.KarryCode.entity.UserLogonLog;
import edu.beihua.KarryCode.repositoryMongo.IUserRepMongo;
import edu.beihua.KarryCode.tools.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/17 下午 6:28
 * @PackageName edu.beihua.KarryCode.repositoryMongo.impl
 * @ClassName UserRepMongo
 * @Description TODO
 * @Version 1.0
 */
@Repository
public class UserRepMongoImpl implements IUserRepMongo {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void Log_UserLoginSuccess(User user, Permissions permissions) {
        try {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            UserLogonLog userLogonLog = UserLogonLog.builder().Permissions(permissions).logonUserName(user.getName()).date(simpleDateFormat.format(date)).Successful(true).Visible(true).build();
            UserLogonLog insert = mongoTemplate.insert(userLogonLog);
            if (insert != null) {
                System.out.println("[Car Rental System 3] ==> 记录登录日志");
            }else {
                System.out.println("[Car Rental System 3] ==> 日志记录失败");
            }
        } catch (Exception e) {
            System.out.println("[Car Rental System 3] ==> 系统内部异常");
        }

    }

    @Override
    public void Log_UserLoginFailure(String visName, Permissions permissions) {
        try {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            UserLogonLog userLogonLog = UserLogonLog.builder().Permissions(permissions).logonUserName(visName).date(simpleDateFormat.format(date)).Successful(false).Visible(true).build();
            UserLogonLog insert = mongoTemplate.insert(userLogonLog);
            if (insert != null) {
                System.out.println("[Car Rental System 3] ==> 记录登录日志");
            }else {
                System.out.println("[Car Rental System 3] ==> 日志记录失败");
            }
        } catch (Exception e) {
            System.out.println("[Car Rental System 3] ==> 系统内部异常");
        }
    }

    @Override
    public List<UserLogonLog> Log_ShowAllUserRepMongo() {
        List<UserLogonLog> mongoTemplateAll = mongoTemplate.findAll(UserLogonLog.class);
        return mongoTemplateAll;
    }

    @Override
    public List<UserLogonLog> Log_ShowPaginationUserRepMongo(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        // 执行分页查询，skip表示跳过的记录数，limit表示每页记录数
        List<UserLogonLog> products = mongoTemplate.find(
                Query.query(Criteria.where("Visible").is(true))
                        .with(pageRequest),
                UserLogonLog.class
        );

        return products;
    }

    @Override
    public long Log_ShowLogCount() {
        long count = mongoTemplate.count(new Query(), UserLogonLog.class);
        return count;
    }
}
