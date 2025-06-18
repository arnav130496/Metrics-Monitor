package com.arnav.metrics_monitor_consumer_app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MetricEvent implements Serializable {
    private String service;
    private String metric;
    private double value;
    private String unit;
    private Instant timestamp;
    private Map<String, String> tags;
}

