//package com.tetrips.chatv2.config;
//
//import com.tetrips.chatv2.model.ChatMessage;
//import org.apache.kafka.clients.admin.AdminClientConfig;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.*;
//import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaConfig {
////
////  @Value("${spring.kafka.producer.bootstrap-servers}")
////  private String producerBootstrapServers;
////
////  @Value("${spring.kafka.consumer.bootstrap-servers}")
////  private String consumerBootstrapServers;
////
////  @Value("${spring.kafka.consumer.group-id}")
////  private String groupId;
//
//  @Value("${kafka.topic.chat.name:chat-topic}")
//  private String topicName;
//
//  @Value("${kafka.topic.chat.partitions:1}")
//  private int partitions;
//
//  @Value("${kafka.topic.chat.replicas:1}")
//  private short replicas;
//
////  @Bean
////  public ProducerFactory<String, ChatMessage> chatMessageProducerFactory() {
////    Map<String, Object> configs = new HashMap<>();
////    configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerBootstrapServers);
////    configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
////    configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
////    return new DefaultKafkaProducerFactory<>(configs);
////  }
////
////  @Bean
////  public KafkaTemplate<String, ChatMessage> chatMessageKafkaTemplate() {
////    return new KafkaTemplate<>(chatMessageProducerFactory());
////  }
////
////  @Bean
////  public ConsumerFactory<String, ChatMessage> chatMessageConsumerFactory() {
////    Map<String, Object> configs = new HashMap<>();
////    configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerBootstrapServers);
////    configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, groupId);
////
////    configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
////    configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
////
////    // Configure the actual deserializer for the delegate
////    configs.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
////    configs.put(JsonDeserializer.VALUE_DEFAULT_TYPE, ChatMessage.class.getName());
////    configs.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
////
////    return new DefaultKafkaConsumerFactory<>(
////            configs,
////            new ErrorHandlingDeserializer<>(new StringDeserializer()),
////            new ErrorHandlingDeserializer<>(new JsonDeserializer<>(ChatMessage.class))
////    );
////  }
////
////  @Bean
////  public ConcurrentKafkaListenerContainerFactory<String, ChatMessage> chatMessageListener() {
////    ConcurrentKafkaListenerContainerFactory<String, ChatMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
////    factory.setConsumerFactory(chatMessageConsumerFactory());
////    return factory;
////  }
//
//  @Bean
//  public KafkaAdmin kafkaAdmin() {
//    Map<String, Object> configs = new HashMap<>();
//    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
//    return new KafkaAdmin(configs);
//  }
//
//  @Bean
//  public NewTopic chatTopic() {
//    return new NewTopic(topicName, partitions, replicas);
//  }
//}