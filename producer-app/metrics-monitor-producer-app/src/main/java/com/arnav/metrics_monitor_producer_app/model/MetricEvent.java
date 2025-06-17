package com.arnav.metrics_monitor_producer_app.model;
import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

public class MetricEvent implements Serializable {
    private String service;
    private String metric;
    private double value;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    private String unit;
    private Instant timestamp;
    private Map<String, String> tags;

    public MetricEvent(String service, String metric, double value, String unit, Instant timestamp, Map<String, String> tags) {
        this.service = service;
        this.metric = metric;
        this.value = value;
        this.unit = unit;
        this.timestamp = timestamp;
        this.tags = tags;
    }
}

