package edu.beihua.KarryCode.repositoryRedis.generic;

import edu.beihua.KarryCode.entity.RankEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/23 下午 7:53
 * @PackageName edu.beihua.KarryCode.repositoryRedis
 * @ClassName RedisRepGeneric
 * @Description TODO
 * @Version 1.0
 */
@Repository
public class RedisRepGeneric {
    @Autowired
    RedisTemplate redisTemplate;
    public void takePointsForScoreLead(String vehicleName,int delta) {
        ZSetOperations<String,String> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.incrementScore("scoreLead",vehicleName, delta);
    }
    public Map<String, Double> ViewLeaderboard(){
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        Map<String,Double> mysqt = new HashMap<>();
        Set<ZSetOperations.TypedTuple<String>> scoreLead = zSetOperations.reverseRangeWithScores("scoreLead", 0, -1);
        for (ZSetOperations.TypedTuple<String> stringTypedTuple : scoreLead) {
            mysqt.put(stringTypedTuple.getValue(),stringTypedTuple.getScore());
        }
        return mysqt;
    }
    public void Update(List<RankEntity> rankEntityList){
        redisTemplate.delete("scoreLead");
        ZSetOperations<String,String> zSetOperations = redisTemplate.opsForZSet();
        for (RankEntity rankEntity : rankEntityList) {
            zSetOperations.incrementScore("scoreLead",rankEntity.getName(), rankEntity.getScore());
        }
    }

}
