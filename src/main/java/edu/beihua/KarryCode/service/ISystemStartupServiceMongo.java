package edu.beihua.KarryCode.service;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/23 下午 1:40
 * @PackageName edu.beihua.KarryCode.service
 * @ClassName ISystemStartupLog
 * @Description TODO
 * @Version 1.0
 */
public interface ISystemStartupServiceMongo {
    void start(String IPAddress);
    void showLogRecentTen();
}
