package edu.beihua.KarryCode.repositoryMongo.impl;

import edu.beihua.KarryCode.entity.MessageLog;
import edu.beihua.KarryCode.entity.UserLogonLog;
import edu.beihua.KarryCode.repositoryMongo.IMessageLogRepMongo;
import edu.beihua.KarryCode.tools.MessageActivity;
import edu.beihua.KarryCode.tools.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Date 2023/9/23 上午 11:48
 * @PackageName edu.beihua.KarryCode.repositoryMongo.impl
 * @ClassName MessageLogImpl
 * @Description TODO
 * @Version 1.0
 */
@Repository
public class MessageLogImplRepMongo implements IMessageLogRepMongo {
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public void Log_VisitorViewAllMessages() {
        try {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            MessageLog messageLog = MessageLog.builder()
                    .messageActivity(MessageActivity.View)
                    .OperationDate(simpleDateFormat.format(date))
                    .Successful(true)
                    .Permissions(Permissions.VISITOR)
                    .Visible(true).build();
            mongoTemplate.insert(messageLog);
        } catch (Exception e) {
            System.out.println("日志更新异常！");
        }
    }

    @Override
    public void Log_CustomerPublishMessage(String customerName,boolean success) {
        try {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            MessageLog messageLog = MessageLog.builder()
                    .messageActivity(MessageActivity.Publish)
                    .OperationDate(simpleDateFormat.format(date))
                    .Operator(customerName)
                    .Successful(success)
                    .Permissions(Permissions.CUSTOMER)
                    .Visible(true).build();
            mongoTemplate.insert(messageLog);
        } catch (Exception e) {
            System.out.println("日志更新异常！");
        }

    }

    @Override
    public void Log_AdminViewMessage(String adminName) {
        try {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            MessageLog messageLog = MessageLog.builder()
                    .messageActivity(MessageActivity.View)
                    .OperationDate(simpleDateFormat.format(date))
                    .Successful(true)
                    .Operator(adminName)
                    .Permissions(Permissions.ADMIN)
                    .Visible(true).build();
            mongoTemplate.insert(messageLog);
        } catch (Exception e) {
            System.out.println("日志更新异常！");
            e.printStackTrace();
        }
    }

    @Override
    public void Log_AdminModifyMessageStatus(String adminName, MessageActivity activity, boolean success) {
        try {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            MessageLog messageLog = MessageLog.builder()
                    .messageActivity(activity)
                    .OperationDate(simpleDateFormat.format(date))
                    .Successful(success)
                    .Operator(adminName)
                    .Permissions(Permissions.ADMIN)
                    .Visible(true).build();
            mongoTemplate.insert(messageLog);
        } catch (Exception e) {
            System.out.println("日志更新异常！");
            e.printStackTrace();
        }
    }

    @Override
    public List<MessageLog> Log_VisitorViewPagination(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        // 执行分页查询，skip表示跳过的记录数，limit表示每页记录数
        List<MessageLog> messageLogs = mongoTemplate.find(
                Query.query(Criteria.where("Visible").is(true))
                        .with(pageRequest),
                MessageLog.class
        );
        return messageLogs;
    }

    @Override
    public long Log_LogCount() {
        return mongoTemplate.count(new Query(), MessageLog.class);
    }


}
