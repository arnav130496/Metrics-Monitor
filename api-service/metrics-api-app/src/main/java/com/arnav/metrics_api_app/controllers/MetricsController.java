package com.arnav.metrics_api_app.controllers;

import com.arnav.metrics_api_app.model.entity.MetricDocument;
import com.arnav.metrics_api_app.repository.MetricRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/metrics")
@RequiredArgsConstructor
@Slf4j
public class MetricsController {
    private final MetricRepository metricRepository;

    @GetMapping
    public List<MetricDocument> getAllMetrics() {
        return metricRepository.findAll();
    }

    @GetMapping("/search")
    public List<MetricDocument> getDataByServiceMetricFilter(@RequestParam(name = "service") String service,
                                                             @RequestParam(name = "metric") String metric,
                                                             @RequestParam(name = "startTime", required = false) Instant startTime,
                                                             @RequestParam(name = "endTime", required = false) Instant endTime) {

        Instant actualStartTime = (startTime != null) ? startTime : Instant.EPOCH;
        Instant actualEndTime = (endTime != null) ? endTime : Instant.now().plus(1, ChronoUnit.MINUTES);
        log.info("Request Data startTime {} endTime {}",actualStartTime, actualEndTime);
        return metricRepository.findByServiceAndMetricAndTimestampBetween(service, metric,actualStartTime,actualEndTime);
    }
}
