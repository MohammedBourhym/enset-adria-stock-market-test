@echo off
echo Building all services...

echo Building discovery-service...
cd discovery-service
call mvnw.cmd clean package -DskipTests
cd ..

echo Building gateway-service...
cd gateway-service
call mvnw.cmd clean package -DskipTests
cd ..

echo Building company-service...
cd company-service
call mvnw.cmd clean package -DskipTests
cd ..

echo Building stock-service...
cd stock-service
call mvnw.cmd clean package -DskipTests
cd ..

echo Building chat-bot-service...
cd chat-bot-service
call mvnw.cmd clean package -DskipTests
cd ..

echo All services built successfully!
echo.
echo Starting Docker Compose...
docker-compose up -d

echo.
echo All services are starting!
echo.
echo Access points:
echo   - Eureka Dashboard: http://localhost:8761
echo   - Gateway Service: http://localhost:8888
echo   - Company Service: http://localhost:8081/api/companies
echo   - Stock Service: http://localhost:8082/api/stocks
echo   - Chat Bot Service: http://localhost:8083
echo   - PostgreSQL: localhost:5432 (user: postgres, password: postgres, db: stockmarket)
echo.
echo Check logs with: docker-compose logs -f [service-name]
echo Stop all services: docker-compose down
