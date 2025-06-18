package com.arnav.metrics_monitor_consumer_app.config;

import com.arnav.metrics_monitor_consumer_app.model.MetricEvent; // Ensure this import is correct
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
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
@Slf4j
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    // Removed @Value("${spring.kafka.consumer.properties.spring.json.value.default.type}")
    // The value will now be hardcoded or taken from a simpler custom property.

    @Bean
    public ConsumerFactory<String, MetricEvent> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);

        // Option 1: Hardcode the class name (recommended for simplicity here)
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, MetricEvent.class.getName());
        // Option 2: Use a simpler custom property (if you want to externalize it)
        // props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, defaultType); // If you define @Value("${app.kafka.consumer.default-type}")

        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");


        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MetricEvent> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MetricEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        return factory;
    }

    @PostConstruct
    public void logKafkaStartup() {
        log.info("Kafka Consumer configured for group {}. Connecting to: {}", groupId, bootstrapServers);
    }
}