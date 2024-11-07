package com.WebSocketChat.redis;

import com.WebSocketChat.handler.WebSocketChatHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Set;

/**
 * Redis에서 수신한 메시지를 처리하는 구독자(Subscriber) 역할
 */
@Service
public class RedisSubscriber {

    private final Set<WebSocketSession> sessions;

    public RedisSubscriber(Set<WebSocketSession> sessions) {
        this.sessions = sessions;
    }

    //Redis의 chat 채널로부터 수신한 메시지를 sessions 목록을 통해 연결된 모든 WebSocket 클라이언트에게 브로드캐스트 한다.
    public void onMessage(String message) {
        System.out.println("Redis에서 수신한 메시지: " + message);
        System.out.println("RedisSubscriber의 현재 세션 목록: " + sessions);
        if (sessions.isEmpty()) {
            System.out.println("활성화된 WebSocket 세션이 없습니다.");
        } else {
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    try {
                        session.sendMessage(new TextMessage(message));
                        System.out.println("WebSocket 세션에 메시지 전송: " + message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("닫힌 WebSocket 세션 발견: " + session.getId());
                }
            }
        }
    }

}
