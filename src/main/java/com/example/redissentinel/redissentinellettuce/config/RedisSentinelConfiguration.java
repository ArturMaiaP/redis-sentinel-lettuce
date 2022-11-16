package com.example.redissentinel.redissentinellettuce.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import static com.example.redissentinel.redissentinellettuce.config.RedisConfigUtil.lettuceConnectionSentinelConfiguration;
import static com.example.redissentinel.redissentinellettuce.config.RedisConfigUtil.redisTemplateConfiguration;

@Configuration
@EnableRedisRepositories
public class RedisSentinelConfiguration {
    private final RedisProperties redisProperties;

    public RedisSentinelConfiguration(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean(name = "redisConnectionFactory")
    @Primary
    public LettuceConnectionFactory lettuceConnectionFactory(){
        return lettuceConnectionSentinelConfiguration(redisProperties);
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String,Object> redisTemplate(@Qualifier("redisConnectionFactory") LettuceConnectionFactory lettuceConnectionFactory){
        return redisTemplateConfiguration(lettuceConnectionFactory);
    }

}
