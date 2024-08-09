package com.tetrips.chatv2.Service;

import com.tetrips.chatv2.model.ChatMessage;
import com.tetrips.chatv2.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {
  private final ChatMessageRepository chatMessageRepository;

  private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

  public Mono<ChatMessage> saveMessage(ChatMessage message) {
    message.setChatTime(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
    kafkaTemplate.send("chat-topic-" + message.getPrId() + "-" + message.getUserId(), message);
    return chatMessageRepository.save(message);
  }

  public Flux<ChatMessage> getAllMessagesByPrId(String prId) {
    return chatMessageRepository.findByPrId(prId);
  }
}