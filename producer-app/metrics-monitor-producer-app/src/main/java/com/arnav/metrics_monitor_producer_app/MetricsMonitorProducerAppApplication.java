package com.arnav.metrics_monitor_producer_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MetricsMonitorProducerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricsMonitorProducerAppApplication.class, args);
	}

}
