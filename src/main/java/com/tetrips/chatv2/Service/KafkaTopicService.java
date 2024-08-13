package com.tetrips.chatv2.Service;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.common.errors.TopicExistsException;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaTopicService {

  private final KafkaAdmin kafkaAdmin;

  public KafkaTopicService(KafkaAdmin kafkaAdmin) {
    this.kafkaAdmin = kafkaAdmin;
  }

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