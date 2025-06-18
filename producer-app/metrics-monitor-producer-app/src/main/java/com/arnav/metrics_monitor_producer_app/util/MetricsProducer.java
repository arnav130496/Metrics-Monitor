package com.arnav.metrics_monitor_producer_app.util;

import com.arnav.metrics_monitor_producer_app.model.MetricEvent;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class MetricsProducer {

    private final KafkaTemplate<String, MetricEvent> kafkaTemplate;

    @Value("${app.kafka.topic}")
    private String topic;

    public MetricsProducer(KafkaTemplate<String, MetricEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(MetricEvent event) {
//        kafkaTemplate.send(topic, event.getService(), event);
        CompletableFuture<SendResult<String, MetricEvent>> future = kafkaTemplate.send(topic, event.getService(), event);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("📤 Sent metric: {} with offset: {}", event, result.getRecordMetadata().offset());
            } else {
                log.error("❌ Failed to send metric: {} due to: {}", event, ex.getMessage(), ex);
                // Implement retry logic or DLQ
            }
        });
    }
    
    @PostConstruct
    public void logKafkaBootstrap() {
        log.info("Kafka is connecting to → " + kafkaTemplate.getProducerFactory().getConfigurationProperties().get("bootstrap.servers"));
    }

}

