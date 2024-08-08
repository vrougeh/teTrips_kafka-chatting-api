package com.tetrips.chatv2.handler;

import com.tetrips.chatv2.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatWebSocketHandler {
  private final SimpMessagingTemplate messagingTemplate;

  @KafkaListener(topics = "processed-chat-topic", groupId = "chat-group")
  public void listen(ChatMessage message) {
    messagingTemplate.convertAndSend("/topic/messages/" + message.getPrId() + "/" + message.getUserId(), message);
  }
}