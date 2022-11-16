package com.example.redissentinel.redissentinellettuce.config;

import io.lettuce.core.ReadFrom;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisConfigUtil {
    public static LettuceConnectionFactory lettuceConnectionSentinelConfiguration(RedisCommonProperties redisCommonProperties){
        LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.ANY)
                .build();

        RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration(
                redisCommonProperties.getSentinelName(),redisCommonProperties.getHost()
        );
        sentinelConfiguration.setSentinelPassword(redisCommonProperties.getSentinelPassword());
        sentinelConfiguration.setPassword(redisCommonProperties.getRedisPassword());

        return new LettuceConnectionFactory(sentinelConfiguration,lettuceClientConfiguration);
    }

    public static RedisTemplate<String, Object> redisTemplateConfiguration(LettuceConnectionFactory lettuceConnectionFactory){
        final RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        template.setEnableTransactionSupport(true);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return template;
    }
}
