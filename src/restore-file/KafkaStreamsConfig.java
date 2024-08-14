//package com.tetrips.chatv2.config;
//
//import com.tetrips.chatv2.model.ChatMessage;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.streams.kstream.Produced;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafkaStreams;
//import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
//import org.springframework.kafka.config.KafkaStreamsConfiguration;
//import org.springframework.kafka.config.StreamsBuilderFactoryBeanConfigurer;
//import org.springframework.kafka.support.serializer.JsonSerde;
//import org.apache.kafka.streams.StreamsConfig;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableKafkaStreams
//public class KafkaStreamsConfig {
//
//  @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
//  public KafkaStreamsConfiguration defaultKafkaStreamsConfig() {
//    Map<String, Object> config = new HashMap<>();
//    config.put(StreamsConfig.APPLICATION_ID_CONFIG, "chat-application");
//    config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
//    config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//    config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class.getName());
//    return new KafkaStreamsConfiguration(config);
//  }
//
//  @Bean
//  public KStream<String, ChatMessage> kStream(StreamsBuilder streamsBuilder) {
//
//    // Ensure the source topic exists
//    KStream<String, ChatMessage> stream = streamsBuilder.stream("chat-topic");
//
//    stream.peek((key, value) -> System.out.println("Received message: " + value))
//            .to("processed-chat-topic", Produced.with(Serdes.String(), new JsonSerde<>(ChatMessage.class)));
//
//    return stream;
//  }
//}