# 📝 Project Notebook: Metrics Monitor

---

## 🚧 Section: Docker Setup & Commands

### ✅ Docker Installation & Prerequisites

* Installed [Docker Desktop](https://www.docker.com/products/docker-desktop/) (Ensure Docker Engine + Docker Compose is available)
* Verified Docker installation:

  ```bash
  docker --version
  docker compose version
  ```

### ⚖️ Docker Compose Configuration

* Created a file `docker/docker-compose.yml` with services:

  * `zookeeper`
  * `kafka`
  * `mongodb`
  * `mongo-express`

### 🚀 Commands Run

#### Start All Containers:

```bash
cd docker/
docker compose up -d
```

#### Check Running Containers:

```bash
docker ps
```

#### View Logs for a Specific Service:

```bash
docker compose logs <service-name>
# Example:
docker compose logs kafka --tail=50
```

#### Restart a Service:

```bash
docker compose restart kafka
```

#### Stop All Containers:

```bash
docker compose down
```

### 🔧 Kafka CLI Access (Inside Container)

```bash
docker exec -it $(docker ps -qf "ancestor=confluentinc/cp-kafka:7.5.0") bash
```

Inside the container:

* Create a topic:

  ```bash
  kafka-topics --bootstrap-server kafka:9092 --create --topic app-metrics --partitions 1 --replication-factor 1
  ```
* List topics:

  ```bash
  kafka-topics --bootstrap-server kafka:9092 --list
  ```
* Exit:

  ```bash
  exit
  ```

### 🌐 Services Access Info

* **Mongo Express:** [http://localhost:8081](http://localhost:8081)

  * Username: `admin`, Password: `password`
* **MongoDB URI:**

  ```bash
  mongodb://admin:password@localhost:27017
  ```

---

(Other sections like Spring Boot, Kafka, MongoDB, etc. to be added next as you build them)
