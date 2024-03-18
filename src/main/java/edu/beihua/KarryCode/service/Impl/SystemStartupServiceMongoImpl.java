package edu.beihua.KarryCode.service.Impl;

import edu.beihua.KarryCode.entity.SystemStartupLog;
import edu.beihua.KarryCode.repositoryMongo.ISystemStartupLogRepMongo;
import edu.beihua.KarryCode.service.ISystemStartupServiceMongo;
import edu.beihua.KarryCode.tools.AccountUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/23 下午 1:40
 * @PackageName edu.beihua.KarryCode.service.Impl
 * @ClassName SystemStartupLogImpl
 * @Description TODO
 * @Version 1.0
 */
@Service
public class SystemStartupServiceMongoImpl implements ISystemStartupServiceMongo {
    @Autowired
    ISystemStartupLogRepMongo iSystemStartupLogRepMongo;

    @Override
    public void start(String IP) {
        try {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            InetAddress localHost = InetAddress.getLocalHost();
            String hostName = localHost.getHostName();
            // * @Author KarryLiu_刘珂瑞 * @Description TODO Mongodb装载
            SystemStartupLog systemStartupLog = SystemStartupLog.builder()
                    .hostName(hostName)
                    .hostAddress(IP)
                    .startTime(simpleDateFormat.format(date))
                    .build();
            iSystemStartupLogRepMongo.startLog(systemStartupLog);
        } catch (UnknownHostException e) {
            System.out.println("IP日志异常：UnknownHostException");
        }
    }

    @Override
    public void showLogRecentTen() {
        List<SystemStartupLog> systemStartupLogNearlyTenArticles = iSystemStartupLogRepMongo.getSystemStartupLogNearlyTenArticles();
        System.out.println("           启动时间           "+"|"+"         启动主机名         "+"|"+"        启动IP地址        ");
        for (SystemStartupLog systemStartupLogNearlyTenArticle : systemStartupLogNearlyTenArticles) {
            String hostName = systemStartupLogNearlyTenArticle.getHostName();
            String hostAddress = systemStartupLogNearlyTenArticle.getHostAddress();
            String startTime = systemStartupLogNearlyTenArticle.getStartTime();
            System.out.println("    "+startTime+"      "+"|"+"     "+hostName+"     "+"|"+"      "+hostAddress+"        ");
        }
        AccountUtility.readReturn();
    }
}
