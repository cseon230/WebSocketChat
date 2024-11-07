package com.WebSocketChat.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisPublisher {

    private final StringRedisTemplate redisTemplate;

    public RedisPublisher(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(String topic, String message) { // 지정된 topic에 메시지를 발행
        redisTemplate.convertAndSend(topic, message);
    }
}
