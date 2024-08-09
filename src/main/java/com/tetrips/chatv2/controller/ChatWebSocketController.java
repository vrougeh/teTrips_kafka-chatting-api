package com.tetrips.chatv2.controller;

import com.tetrips.chatv2.Service.ChatService;
import com.tetrips.chatv2.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatWebSocketController {

  private final ChatService chatService;

  private final SimpMessagingTemplate messagingTemplate;



  @MessageMapping("/send")
  public void sendMessage(ChatMessage message) {
    chatService.saveMessage(message).block();
  }

  @KafkaListener(topics = "chat-topic-#", groupId = "chat-group")
  public void listen(ChatMessage message) {
    messagingTemplate.convertAndSend("/topic/" + message.getPrId(), message);
  }
}
