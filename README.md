# Stock Market Microservices - Docker Compose

This project contains a microservices architecture for a stock market application.

## Services

- **Discovery Service** (Eureka): Port 8761
- **Gateway Service**: Port 8888
- **Company Service**: Port 8081
- **Stock Service**: Port 8082
- **Chat Bot Service**: Port 8083
- **PostgreSQL Database**: Port 5432

## Prerequisites

- Java 17+
- Docker & Docker Compose
- Maven

## Quick Start

### Build and Run All Services

**Linux/Mac:**
```bash
chmod +x build-and-run.sh
./build-and-run.sh
```

**Windows:**
```cmd
build-and-run.bat
```

### Manual Build

1. Build all services:
```bash
# Discovery Service
cd discovery-service && ./mvnw clean package -DskipTests && cd ..

# Gateway Service
cd gateway-service && ./mvnw clean package -DskipTests && cd ..

# Company Service
cd company-service && ./mvnw clean package -DskipTests && cd ..

# Stock Service
cd stock-service && ./mvnw clean package -DskipTests && cd ..

# Chat Bot Service
cd chat-bot-service && ./mvnw clean package -DskipTests && cd ..
```

2. Start with Docker Compose:
```bash
docker-compose up -d
```

## Access Points

- **Eureka Dashboard**: http://localhost:8761
- **API Gateway**: http://localhost:8888
- **Company Service**: http://localhost:8888/company/api/companies
- **Stock Service**: http://localhost:8888/stock/api/stocks
- **PostgreSQL**: localhost:5432
  - Database: `stockmarket`
  - Username: `postgres`
  - Password: `postgres`

## API Examples

### Company Service

**Create Company:**
```bash
curl -X POST http://localhost:8888/company/api/companies \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Tech Corp",
    "ipoDate": "2024-01-01",
    "currentStockPrice": 150.50,
    "domain": "IT"
  }'
```

**Get All Companies:**
```bash
curl http://localhost:8888/company/api/companies
```

**Get Companies by Domain:**
```bash
curl http://localhost:8888/company/api/companies/domain/IT
```

### Stock Service

**Add Stock Quotation:**
```bash
curl -X POST http://localhost:8888/stock/api/stocks \
  -H "Content-Type: application/json" \
  -d '{
    "date": "2024-11-17",
    "openValue": 150.00,
    "highValue": 155.00,
    "lowValue": 148.00,
    "closeValue": 152.50,
    "volume": 1000000,
    "companyId": 1
  }'
```

**Get All Stocks:**
```bash
curl http://localhost:8888/stock/api/stocks
```

**Get Stocks by Company:**
```bash
curl http://localhost:8888/stock/api/stocks/company/1
```

## Docker Commands

**View logs:**
```bash
docker-compose logs -f [service-name]
```

**Stop all services:**
```bash
docker-compose down
```

**Stop and remove volumes:**
```bash
docker-compose down -v
```

**Rebuild a service:**
```bash
docker-compose up -d --build [service-name]
```

## Environment Variables

Each service can be configured using environment variables in `docker-compose.yml`:

- `SPRING_DATASOURCE_URL`: Database URL
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password
- `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE`: Eureka server URL
- `JAVA_OPTS`: JVM options (e.g., `-Xms256m -Xmx512m`)

## Development Mode

To run services locally without Docker (using H2 database):

```bash
cd [service-name]
./mvnw spring-boot:run
```

Services will use H2 in-memory database by default when environment variables are not set.
