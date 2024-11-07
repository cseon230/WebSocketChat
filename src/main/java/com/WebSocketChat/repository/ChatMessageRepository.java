package com.WebSocketChat.repository;

import com.WebSocketChat.domain.ChatMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessage, String> {
    // CrudRepository를 상속받았기 때문에 Spring Data Redis가 자동으로 CURD 메서드를 제공하고,
    // 이 메서드들을 통해 Redis와 통신하게 된다.
    // SpringBoot는 프로젝트에 Spring Data Redis와 관련된 의존성이 있으면 자동으로 Redis와의 연결을 구성한다.
}
