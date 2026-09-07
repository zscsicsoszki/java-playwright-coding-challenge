FROM mcr.microsoft.com/playwright/java:v1.56.0-noble

WORKDIR /app

# Copy Maven descriptor first
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy project
COPY . .

# Execute UI tests
CMD ["mvn", "clean", "install", "-Pui-tests", "-Dheadless=true"]