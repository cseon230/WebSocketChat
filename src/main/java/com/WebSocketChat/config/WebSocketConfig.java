package com.WebSocketChat.config;

import com.WebSocketChat.handler.WebSocketChatHandler;
import com.WebSocketChat.redis.RedisPublisher;
import com.WebSocketChat.redis.RedisSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final RedisPublisher redisPublisher;

    public WebSocketConfig(RedisPublisher redisPublisher) {
        this.redisPublisher = redisPublisher;
    }

    @Bean
    public Set<WebSocketSession> sessions() {
        return Collections.synchronizedSet(new HashSet<>()); // 싱글톤 세션 빈
    }

    @Bean
    public WebSocketHandler chatHandler(RedisPublisher redisPublisher, Set<WebSocketSession> sessions) {
        return new WebSocketChatHandler(redisPublisher, sessions); // RedisPublisher와 sessions 주입
    }

    @Bean
    public RedisSubscriber redisSubscriber(Set<WebSocketSession> sessions) {
        return new RedisSubscriber(sessions); // RedisSubscriber에 동일한 세션 빈 주입
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler(redisPublisher, sessions()), "/chat") // WebSocket 핸들러를 /chat 앤드포인트에 연결
                .setAllowedOrigins("*"); //모든 도메인에서 접근이 가능하도록 설정
    }

}
