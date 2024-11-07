package com.WebSocketChat.handler;

import com.WebSocketChat.redis.RedisPublisher;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@Getter
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final RedisPublisher redisPublisher;
    private final Set<WebSocketSession> sessions;

    public WebSocketChatHandler(RedisPublisher redisPublisher, Set<WebSocketSession> sessions) {
        this.redisPublisher = redisPublisher;
        this.sessions = sessions;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 클라이언트로부터 메세지를 수신하고 해당 메시지를 redisPublisher를 통해 Redis의 chat 채널에 발행한다
        redisPublisher.publish("chat", message.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session); // 클라이언트가 연결되면 해당 세션을 세션 목록에 추가
        System.out.println("새 WebSocket 세션 추가: " + session.getId());
        System.out.println("현재 세션 목록: " + sessions);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session); // 클라이언트가 연결을 종료하면 세션 목록에서 제거
        System.out.println("WebSocket 세션 제거: " + session.getId());
        System.out.println("현재 세션 목록: " + sessions);
    }
}
