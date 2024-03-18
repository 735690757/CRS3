package edu.beihua.KarryCode.repositoryMongo;

import edu.beihua.KarryCode.entity.MessageLog;
import edu.beihua.KarryCode.tools.MessageActivity;

import java.util.List;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/23 上午 11:47
 * @PackageName edu.beihua.KarryCode.repositoryMongo
 * @ClassName IMessageLog
 * @Description TODO
 * @Version 1.0
 */
public interface IMessageLogRepMongo {
    void Log_VisitorViewAllMessages();

    void Log_CustomerPublishMessage(String customerName, boolean success);
    void Log_AdminViewMessage(String adminName);
    void Log_AdminModifyMessageStatus(String admin, MessageActivity active, boolean success);
    List<MessageLog> Log_VisitorViewPagination(int pageNumber, int pageSize);
    long Log_LogCount();
}