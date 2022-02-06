package com.gcc.oa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * @className: RedisAutoConfig
 * @author: 小李探花
 * @date: 2022/1/27 18:35
 * @description:
 */
@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisAutoConfig {
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Resource
    JedisPoolConfig jedisPoolConfig;

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.timeout}")
    private int timeout;

    @Bean
    public JedisPool jedisPool() {
        return new JedisPool(jedisPoolConfig, host, port, timeout);
    }

}

