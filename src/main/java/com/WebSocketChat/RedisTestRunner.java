package com.WebSocketChat;

import com.WebSocketChat.domain.ChatMessage;
import com.WebSocketChat.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RedisTestRunner implements CommandLineRunner {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public void run(String... args) throws Exception {
        // Redis에 데이터 저장
        ChatMessage message = new ChatMessage("1", "Hello, Redis with Spring Boot!");
        chatMessageRepository.save(message);

        // Redis에서 데이터 조회
        chatMessageRepository.findById("1").ifPresent(chatMessage ->
                System.out.println("Retrieved message : " + chatMessage.getContent()));
    }

}
