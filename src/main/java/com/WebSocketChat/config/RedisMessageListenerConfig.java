package com.WebSocketChat.config;

import com.WebSocketChat.redis.RedisSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisMessageListenerConfig {

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory,
                                                                       MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer(); // Redis에서 특정 주제(topic)의 메시지를 수신하여, 등록된 리스너에게 전달
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("chat")); // 주제(topic)를 "chat"으로 지정하여, 모든 "chat" 주제의 메시지를 수신
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RedisSubscriber subscriber) {
        // 메시지를 수신하면 RedisSubscriber의 onMessage 메서드를 호출
        return new MessageListenerAdapter(subscriber, "onMessage");
    }
}
