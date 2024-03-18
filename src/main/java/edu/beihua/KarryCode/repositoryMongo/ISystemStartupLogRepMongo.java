package edu.beihua.KarryCode.repositoryMongo;

import edu.beihua.KarryCode.entity.SystemStartupLog;

import java.util.List;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/23 下午 1:48
 * @PackageName edu.beihua.KarryCode.repositoryMongo
 * @ClassName ISystemStartupLog
 * @Description TODO
 * @Version 1.0
 */
public interface ISystemStartupLogRepMongo {
    void startLog(SystemStartupLog systemStartupLog);
    List<SystemStartupLog> getSystemStartupLogNearlyTenArticles();
}
