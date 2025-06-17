package com.arnav.metrics_monitor_producer_app.config;

import com.arnav.metrics_monitor_producer_app.model.MetricEvent;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${app.kafka.topic}")
    private String topic;

    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Value("${spring.kafka.producer.retries}")
    private int retries;
    @Bean
    public ProducerFactory<String, MetricEvent> producerFactory() {

        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        props.put(ProducerConfig.ACKS_CONFIG, acks);
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);

//        props.put(ProducerConfig.LINGER_MS_CONFIG, 10); // Wait up to 10ms to batch
//        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16_384); // 16 KB
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, MetricEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @PostConstruct
    public void logKafkaStartup() {
        log.info("Kafka Producer configured to connect to: {}", bootstrapServers);
    }

}

