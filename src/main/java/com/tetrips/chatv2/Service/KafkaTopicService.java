package com.tetrips.chatv2.Service;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.errors.TopicExistsException;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class KafkaTopicService {

  private final KafkaAdmin kafkaAdmin;


  public void createTopicIfNotExists(String topicName, int numPartitions, short replicationFactor) {
    Map<String, Object> configs = kafkaAdmin.getConfigurationProperties();
    try (AdminClient adminClient = AdminClient.create(configs)) {
      NewTopic newTopic = new NewTopic(topicName, numPartitions, replicationFactor);
      adminClient.createTopics(Collections.singleton(newTopic)).all().get();
      System.out.println("Topic created: " + topicName);
    } catch (InterruptedException | ExecutionException e) {
      if (e.getCause() instanceof TopicExistsException) {
        System.out.println("Topic already exists: " + topicName);
      } else {
        e.printStackTrace();
      }
    }
  }
}