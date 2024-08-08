package com.tetrips.chatv2.repository;

import com.tetrips.chatv2.model.ChatMessage;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface ChatMessageRepository extends ReactiveMongoRepository<ChatMessage, String> {
  Flux<ChatMessage> findByPrId(String prId);
}