#!/bin/bash

echo "🔨 Building all services..."

# Build discovery-service
echo "Building discovery-service..."
cd discovery-service
./mvnw clean package -DskipTests
cd ..

# Build gateway-service
echo "Building gateway-service..."
cd gateway-service
./mvnw clean package -DskipTests
cd ..

# Build company-service
echo "Building company-service..."
cd company-service
./mvnw clean package -DskipTests
cd ..

# Build stock-service
echo "Building stock-service..."
cd stock-service
./mvnw clean package -DskipTests
cd ..

# Build chat-bot-service
echo "Building chat-bot-service..."
cd chat-bot-service
./mvnw clean package -DskipTests
cd ..

echo "✅ All services built successfully!"
echo ""
echo "🐳 Starting Docker Compose..."
docker-compose up -d

echo ""
echo "✅ All services are starting!"
echo ""
echo "📍 Access points:"
echo "  - Eureka Dashboard: http://localhost:8761"
echo "  - Gateway Service: http://localhost:8888"
echo "  - Company Service: http://localhost:8081/api/companies"
echo "  - Stock Service: http://localhost:8082/api/stocks"
echo "  - Chat Bot Service: http://localhost:8083"
echo "  - PostgreSQL: localhost:5432 (user: postgres, password: postgres, db: stockmarket)"
echo ""
echo "📊 Check logs with: docker-compose logs -f [service-name]"
echo "🛑 Stop all services: docker-compose down"
