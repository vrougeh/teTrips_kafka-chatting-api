package com.tetrips.chatv2.config;

import com.tetrips.chatv2.model.ChatMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

  @Value("${spring.kafka.consumer.bootstrap-servers}")
  private String consumerBootstrapServers;

  @Value("${spring.kafka.consumer.group-id}")
  private String groupId;

  @Bean
  public ConsumerFactory<String, ChatMessage> chatMessageConsumerFactory() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
    configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, "group_id");
    configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
    configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

    // Configure the actual deserializer for the delegate
    configs.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
    configs.put(JsonDeserializer.VALUE_DEFAULT_TYPE, ChatMessage.class.getName());
    configs.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

    return new DefaultKafkaConsumerFactory<>(
            configs //,
//            new ErrorHandlingDeserializer<>(new StringDeserializer()),
//            new ErrorHandlingDeserializer<>(new JsonDeserializer<>(ChatMessage.class))
    );
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, ChatMessage> chatMessageListener() {
    ConcurrentKafkaListenerContainerFactory<String, ChatMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(chatMessageConsumerFactory());
    return factory;
  }

}
