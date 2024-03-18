package edu.beihua.KarryCode;


import edu.beihua.KarryCode.entity.RankEntity;
import edu.beihua.KarryCode.repositoryMongo.impl.RankRepImpl;
import edu.beihua.KarryCode.repositoryRedis.generic.RedisRepGeneric;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/24 下午 4:50
 * @PackageName edu.beihua.KarryCode
 * @ClassName MongoRedisConnTest
 * @Description TODO
 * @Version 1.0
 */
public class MongoRedisConnTest extends SpringbootTest{
    @Autowired
    RedisRepGeneric redisRepGeneric;
    @Autowired
    RankRepImpl rankRep;
    @Test
    public void RedisToMongodb(){
        Map<String, Double> stringDoubleMap = redisRepGeneric.ViewLeaderboard();
        ArrayList<RankEntity> rankEntities = new ArrayList<>();
        for (String VName : stringDoubleMap.keySet()) {
            Double score = stringDoubleMap.get(VName);
            rankEntities.add(new RankEntity(VName, score));
        }
        rankRep.Write(rankEntities);
    }
    @Test
    public void MongodbToRedis(){
        List<RankEntity> rankEntities = rankRep.Read();
        redisRepGeneric.Update(rankEntities);
    }
}
