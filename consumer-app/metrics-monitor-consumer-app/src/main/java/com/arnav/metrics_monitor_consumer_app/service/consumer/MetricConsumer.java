package com.arnav.metrics_monitor_consumer_app.service.consumer;

import com.arnav.metrics_monitor_consumer_app.model.entity.MetricDocument;
import com.arnav.metrics_monitor_consumer_app.model.MetricEvent;
import com.arnav.metrics_monitor_consumer_app.repository.MetricRepository; // Assuming this is correct
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; // Changed to Slf4j for Lombok logger
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetricConsumer {

    private final MetricRepository metricRepository;

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(MetricEvent metricEvent) {
        log.info("Received metric event from Kafka: {}", metricEvent);
        MetricDocument metricDocument = MetricDocument.fromEvent(metricEvent);
        try {
            metricRepository.save(metricDocument);
            log.info("Successfully saved metric event to MongoDB with ID: {}", metricDocument.getId());
        } catch (Exception e) {
            log.error("Failed to save metric event to MongoDB: {}", metricEvent, e);
        }
    }
}