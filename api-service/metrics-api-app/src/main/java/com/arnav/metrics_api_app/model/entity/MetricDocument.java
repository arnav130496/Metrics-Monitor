package com.arnav.metrics_api_app.model.entity;

import com.arnav.metrics_api_app.model.MetricEvent;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.util.Map;

@Data
@Document(collection = "metrics")
@CompoundIndex(name = "metric_service_timestamp_idx", def = "{'metric': 1, 'service': 1, 'timestamp': -1}")
@CompoundIndex(name = "service_metric_timestamp_idx", def = "{'service': 1, 'metric': 1, 'timestamp': -1}")
public class MetricDocument {

    @Id
    private String id;

    @Indexed
    private String service;

    @Indexed
    private String metric;

    private double value;
    private String unit;

    @Indexed(direction = org.springframework.data.mongodb.core.index.IndexDirection.DESCENDING) // Essential for time-series queries
    private Instant timestamp;
    private Map<String, String> tags;

    // For better immutability of the 'tags' map if you use @Data
    public void setTags(Map<String, String> tags) {
        // Create an unmodifiable map to prevent external modification after setting
        this.tags = tags != null ? Map.copyOf(tags) : null;
    }


    public static MetricDocument fromEvent(MetricEvent event) {
        MetricDocument doc = new MetricDocument();
        // Assuming MetricEvent's getters are available (either @Data or @Value)
        doc.setService(event.getService());
        doc.setMetric(event.getMetric());
        doc.setValue(event.getValue());
        doc.setUnit(event.getUnit());
        doc.setTimestamp(event.getTimestamp());
        doc.setTags(event.getTags()); // Uses the improved setter if present
        return doc;
    }
}