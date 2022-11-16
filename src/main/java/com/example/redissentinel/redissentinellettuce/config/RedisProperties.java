package com.example.redissentinel.redissentinellettuce.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spring.redis")
public class RedisProperties extends RedisCommonProperties{
}
