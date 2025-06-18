package com.arnav.metrics_monitor_consumer_app;

import com.arnav.metrics_monitor_consumer_app.model.MetricEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

import java.time.Instant;
import java.util.Map;

@SpringBootApplication
public class MetricsMonitorConsumerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricsMonitorConsumerAppApplication.class, args);
	}

}
