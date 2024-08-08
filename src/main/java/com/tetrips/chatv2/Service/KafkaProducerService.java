//package com.tetrips.chatv2.Service;
//
//import com.tetrips.chatv2.model.ChatMessage;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaProducerService {
//
//  private final KafkaTemplate<String, ChatMessage> kafkaTemplate;
//
//  public KafkaProducerService(KafkaTemplate<String, ChatMessage> kafkaTemplate) {
//    this.kafkaTemplate = kafkaTemplate;
//  }
//
//  public void sendMessage(String topic, ChatMessage message) {
//    kafkaTemplate.send(topic, message);
//  }
//}
