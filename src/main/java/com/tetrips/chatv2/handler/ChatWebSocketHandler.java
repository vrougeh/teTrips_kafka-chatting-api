package com.tetrips.chatv2.handler;

import com.tetrips.chatv2.Service.KafkaTopicService;
import com.tetrips.chatv2.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatWebSocketHandler {
  private final SimpMessagingTemplate messagingTemplate;
  private final KafkaTopicService kafkaTopicService;

  @KafkaListener(topics = "processed-chat-topic", groupId = "chat-group")
  public void listen(ChatMessage message) {

    kafkaTopicService.createTopicIfNotExists("processed-chat-topic", 180, (short) 3);
    messagingTemplate.convertAndSend("/topic/messages/" + message.getPrId(), message);
  }
}