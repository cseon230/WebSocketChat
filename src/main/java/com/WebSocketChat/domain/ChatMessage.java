package com.WebSocketChat.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("ChatMessage")
public class ChatMessage {

    @Id
    private String id;
    private String content;

    public ChatMessage(String id, String content) {
        this.id = id;
        this.content = content;
    }

    // Getter와 Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
