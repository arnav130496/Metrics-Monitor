package com.arnav.metrics_api_app.repository;

import com.arnav.metrics_api_app.model.entity.MetricDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface MetricRepository extends MongoRepository<MetricDocument, String> {

    List<MetricDocument> findByServiceAndMetric(String service, String metric);
    List<MetricDocument> findByServiceAndMetricAndTimestampBetween(String service, String metric, Instant startTime, Instant endTime);

}
