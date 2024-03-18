package edu.beihua.KarryCode.repositoryMongo.impl;

import edu.beihua.KarryCode.entity.SystemStartupLog;
import edu.beihua.KarryCode.repositoryMongo.ISystemStartupLogRepMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/23 下午 1:50
 * @PackageName edu.beihua.KarryCode.repositoryMongo.impl
 * @ClassName SystemStartupLogImpl
 * @Description TODO
 * @Version 1.0
 */
@Repository
public class SystemStartupLogImpl implements ISystemStartupLogRepMongo {
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public void startLog(SystemStartupLog systemStartupLog) {
        try {
            mongoTemplate.insert(systemStartupLog);
        } catch (Exception e) {
            System.out.println("Mongodb插入异常");
        }

    }

    @Override
    public List<SystemStartupLog> getSystemStartupLogNearlyTenArticles() {
        Sort sort = Sort.by(Sort.Order.desc("startTime"));

        // 使用排序和limit来查询最近的十条日志记录
        List<SystemStartupLog> recentLogs = mongoTemplate.find(
                Query.query(new Criteria()).with(sort).limit(10),
                SystemStartupLog.class
        );
        return recentLogs;
    }
}
