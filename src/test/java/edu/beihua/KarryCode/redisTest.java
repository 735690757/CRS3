package edu.beihua.KarryCode;

import edu.beihua.KarryCode.Configs.StringRedisTemplate;
import edu.beihua.KarryCode.service.Impl.RedisServiceGenericImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.*;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/23 下午 3:39
 * @PackageName edu.beihua.KarryCode
 * @ClassName redisTest
 * @Description TODO
 * @Version 1.0
 */
public class redisTest extends SpringbootTest{
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void test(){
        redisTemplate.opsForValue().set("emailX", "example@example.com");

        // 获取"email"键的值
        String s = (String) redisTemplate.opsForValue().get("emailX");
        System.out.println(s);

    }
    @Test
    public void zu(){

        ZSetOperations<String,String> zSetOperations = redisTemplate.opsForZSet();
        // 向有序集合添加成员和分数
//        zSetOperations.add("scoreLead","ttb", 6);
        zSetOperations.incrementScore("scoreLead","121", 6);

    }
    @Test
    public void sel(){
        ZSetOperations<String,String> zSetOperations = redisTemplate.opsForZSet();
        Map<String,Double> mysqt = new HashMap<>();
        Set<ZSetOperations.TypedTuple<String>> scoreLead = zSetOperations.reverseRangeWithScores("scoreLead", 0, -1);
        for (ZSetOperations.TypedTuple<String> stringTypedTuple : scoreLead) {
            mysqt.put(stringTypedTuple.getValue(),stringTypedTuple.getScore());
        }
        System.out.println(mysqt);
    }
    @Autowired
    RedisServiceGenericImpl redisServiceGeneric;
    @Test
    public void testX(){
        redisServiceGeneric.QueryLeaderboard();
    }
}
