package com.WebSocketChat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    // Redis와의 연결을 설정하고, RedisTemplate를 빈으로 등록하는 설정 파일
    // RedisTemplate는 Redis와의 데이터 읽기, 쓰기, 삭제 등의 작업을 수행할 수 있는 기본 도구가 된다
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
