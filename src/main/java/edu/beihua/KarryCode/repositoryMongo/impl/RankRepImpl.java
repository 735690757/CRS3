package edu.beihua.KarryCode.repositoryMongo.impl;

import edu.beihua.KarryCode.entity.RankEntity;
import edu.beihua.KarryCode.repositoryMongo.IRankRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/24 下午 4:53
 * @PackageName edu.beihua.KarryCode.repositoryMongo.impl
 * @ClassName RankRepImpl
 * @Description TODO
 * @Version 1.0
 */
@Repository
public class RankRepImpl implements IRankRep {
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public void Write(ArrayList<RankEntity> rankEntities) {
        for (RankEntity rankEntity : rankEntities) {
            Query query = new Query(Criteria.where("name").is(rankEntity.getName()));
            Update update = new Update()
                    .set("name", rankEntity.getName())
                    .set("score", rankEntity.getScore());

            mongoTemplate.upsert(query, update, RankEntity.class);
        }
    }

    @Override
    public List<RankEntity> Read() {
        List<RankEntity> rankEntityList = mongoTemplate.findAll(RankEntity.class);
        return rankEntityList;
    }
}
