package edu.beihua.KarryCode.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/23 下午 4:05
 * @PackageName edu.beihua.KarryCode.Configs
 * @ClassName RedisTemplateConfig
 * @Description TODO
 * @Version 1.0
 */
@Component("RedisTemplateX")
public class RedisTemplateConfig {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Bean
    public RedisTemplate<String, Object> stringSerializerRedisTemplate() {

        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        return redisTemplate;
    }
}
