# 📊 Metrics Monitor

A mini backend system to simulate and monitor application metrics using Kafka, MongoDB, Spring Boot, Docker, and Grafana.

---

## 🧱 Components

### 1. Metrics Generator (Kafka Producer)

* Generates synthetic metrics
* Sends data to Kafka topic `app-metrics`

### 2. Metrics Consumer (Kafka Consumer)

* Consumes data from `app-metrics`
* Stores it in MongoDB

### 3. Metrics API (Spring Boot REST API)

* Queries MongoDB to return metrics
* Endpoints support time filters, aggregation, etc.

### 4. Visualization (Grafana)

* Dashboards showing metrics from MongoDB (Optional for stretch goal)

### 5. React UI (Stretch Goal)

* Build a simple front-end to visualize metrics via API

### 6. AWS Deployment (Stretch Goal)

* Deploy components on AWS for real-world cloud experience

---

## 📦 Tech Stack

* Apache Kafka
* MongoDB
* Spring Boot (Java 17)
* Docker + Docker Compose
* Grafana (Optional)
* React (Optional)
* AWS (Optional)

---

## 🧪 Kafka Message Schema (v1)

```json
{
  "service": "payment-service",
  "metric": "cpu_usage",
  "value": 73.5,
  "unit": "%",
  "timestamp": "2025-06-16T12:30:00Z",
  "tags": {
    "region": "us-east-1",
    "instance": "i-abc123"
  }
}
```

---

## 🗄 MongoDB Document Structure

```json
{
  "_id": "ObjectId",
  "service": "payment-service",
  "metric": "cpu_usage",
  "value": 73.5,
  "unit": "%",
  "timestamp": ISODate("2025-06-16T12:30:00Z"),
  "tags": {
    "region": "us-east-1",
    "instance": "i-abc123"
  }
}
```

> Index on `timestamp`, `metric`, and maybe `service` for efficient queries.

---

## 🛠 Docker Setup

* `zookeeper` – Required by Kafka
* `kafka` – Message broker for communication
* `mongodb` – NoSQL data store
* `mongo-express` – MongoDB UI

### Run the stack:

```bash
cd docker/
docker compose up -d
```

## ✨ License

This is a learning project. No license is applied.
