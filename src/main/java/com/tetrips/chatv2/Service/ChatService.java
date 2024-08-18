package com.tetrips.chatv2.Service;

import com.tetrips.chatv2.model.ChatMessage;
import com.tetrips.chatv2.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class ChatService {
  private final ChatMessageRepository chatMessageRepository;

  private final KafkaTemplate<String, ChatMessage> kafkaTemplate;
  private final KafkaTopicService kafkaTopicService;

  public Mono<ChatMessage> saveMessage(ChatMessage message) {
    message.setChatTime(LocalDateTime.now(ZoneId.of("Asia/Seoul")));

    // 동적 토픽 이름 생성
    String topicName = "chat-topic-" + message.getPrId();

    // 토픽이 존재하지 않을 경우, 생성
    kafkaTopicService.createTopicIfNotExists(topicName, 180, (short) 3);

//    kafkaTemplate.send(topicName + "-" + message.getUserId(), message); //set dynamic topic name
    kafkaTemplate.send(topicName, message);  //set dynamic topic name
//    kafkaTemplate.send("processed-chat-topic", message); //set static topic name

    return chatMessageRepository.save(message);
  }

  public Flux<ChatMessage> getAllMessagesByPrId(String prId) {
    return chatMessageRepository.findByPrId(prId);
  }
}