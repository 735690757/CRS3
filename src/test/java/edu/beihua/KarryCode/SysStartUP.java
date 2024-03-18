package edu.beihua.KarryCode;

import edu.beihua.KarryCode.entity.SystemStartupLog;
import edu.beihua.KarryCode.repositoryMongo.ISystemStartupLogRepMongo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/10/8 下午 4:06
 * @PackageName edu.beihua.KarryCode
 * @ClassName SysStartUP
 * @Description TODO
 * @Version 1.0
 */
public class SysStartUP extends SpringbootTest{
    @Autowired
    ISystemStartupLogRepMongo iSystemStartupLogRepMongo;
    @Test
    public void test(){
        List<SystemStartupLog> systemStartupLogNearlyTenArticles = iSystemStartupLogRepMongo.getSystemStartupLogNearlyTenArticles();
        for (SystemStartupLog systemStartupLogNearlyTenArticle : systemStartupLogNearlyTenArticles) {
            System.out.println("systemStartupLogNearlyTenArticle = " + systemStartupLogNearlyTenArticle);
        }
    }
}
