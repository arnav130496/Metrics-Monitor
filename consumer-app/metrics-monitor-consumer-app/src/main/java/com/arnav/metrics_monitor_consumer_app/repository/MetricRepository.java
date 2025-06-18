package com.arnav.metrics_monitor_consumer_app.repository;

import com.arnav.metrics_monitor_consumer_app.model.entity.MetricDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends MongoRepository<MetricDocument, String> {
}
