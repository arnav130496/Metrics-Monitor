📊 Metrics Monitor – Kafka Producer App
This is the Producer module for the Metrics Monitor project. It simulates metric events (like CPU usage, memory, etc.) for various services and publishes them to a Kafka topic at fixed intervals. These events are later consumed and stored for monitoring and visualization.

✅ Features
Periodically generates synthetic metrics

Publishes events to a Kafka topic using Spring Kafka

Configurable via application.yml

Clean architecture with separation of concerns

🏗️ Project Structure
pgsql
Copy
Edit
metrics-monitor-producer-app
│
├── config/              # Kafka configuration
├── model/               # MetricEvent data model
├── scheduler/           # Metric publisher (simulates service metrics)
├── util/                # Kafka producer wrapper
├── application.yml      # Spring Boot configuration
└── MetricsProducerApp   # Main application
⚙️ Configuration
Update application.yml as needed:

yaml
Copy
Edit
spring:
  kafka:
    bootstrap-servers: localhost:9092
    producer:
      acks: all
      retries: 3
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
    properties:
      spring.json.add.type.headers: false

app:
  kafka:
    topic: app-metrics
🚀 Running the App
🧱 Prerequisites
Java 17+

Kafka running on localhost:9092 (or update the config)

Docker (optional for running Kafka)

🏁 Steps
Start Kafka (via Docker or your preferred method)

Run the app:

bash
Copy
Edit
./gradlew bootRun   # or use your IDE
Check logs for emitted metrics every 5 seconds.

📦 Sample MetricEvent
json
Copy
Edit
{
  "service": "order-service",
  "metric": "cpu_usage",
  "value": 65.43,
  "unit": "%",
  "timestamp": "2025-06-17T13:30:00Z",
  "tags": {
    "env": "dev",
    "region": "us-east"
  }
}
🧪 Test Your Kafka Setup (Optional)
To ensure events are reaching Kafka, you can run a simple Kafka consumer CLI:

bash
Copy
Edit
kafka-console-consumer --bootstrap-server localhost:9092 --topic app-metrics --from-beginning
👷 Future Enhancements
Make metric patterns configurable via YAML

Add unit tests and integration tests

Add actuator metrics to monitor producer itself

Add retry & DLQ (dead letter queue) handling