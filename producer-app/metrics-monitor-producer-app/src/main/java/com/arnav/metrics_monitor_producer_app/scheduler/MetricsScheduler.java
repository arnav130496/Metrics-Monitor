package com.arnav.metrics_monitor_producer_app.scheduler;


import com.arnav.metrics_monitor_producer_app.model.MetricEvent;
import com.arnav.metrics_monitor_producer_app.util.MetricsProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class MetricsScheduler {

    private final MetricsProducer producer;
    private static final Logger log = LoggerFactory.getLogger(MetricsScheduler.class);

    public MetricsScheduler(MetricsProducer producer) {
        this.producer = producer;
    }

    private final Random random = new Random();

    private final List<String> services = List.of("order-service", "user-service", "inventory-service");
    private final List<String> metrics = List.of("cpu_usage", "memory_usage", "disk_io");

    @Scheduled(fixedRate = 5000)
    public void publishMetric() {
        log.info("Publishing metric");
        String service = getRandom(services);
        String metric = getRandom(metrics);
        double value = random.nextDouble() * 100;

        MetricEvent event = new MetricEvent(
                service,
                metric,
                Math.round(value * 100.0) / 100.0,
                "%",
                Instant.now(),
                Map.of("env", "dev", "region", "us-east")
        );
        producer.send(event);
        log.info("Published Metric {}",event);
    }

    private String getRandom(List<String> list) {
        return list.get(random.nextInt(list.size()));
    }
}

