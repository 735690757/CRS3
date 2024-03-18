package edu.beihua.KarryCode.Configs;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/23 下午 3:57
 * @PackageName edu.beihua.KarryCode.Configs
 * @ClassName StringRedisTemplate
 * @Description TODO
 * @Version 1.0
 */
public class StringRedisTemplate extends RedisTemplate<String,String> {
    public StringRedisTemplate(){
        this.setKeySerializer(RedisSerializer.string());
        this.setValueSerializer(RedisSerializer.string());
        this.setHashKeySerializer(RedisSerializer.string());
        this.setHashValueSerializer(RedisSerializer.string());
    }
}
