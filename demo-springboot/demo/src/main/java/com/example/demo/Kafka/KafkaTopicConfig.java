package com.example.demo.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic transferTopic() {
        return TopicBuilder
                .name("transfer-topic")
                .build();
    }
}

