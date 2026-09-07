FROM maven:3.9.11-eclipse-temurin-21

WORKDIR /app

# Copy Maven descriptor first for dependency caching
COPY pom.xml .

RUN mvn dependency:go-offline

# Copy project sources
COPY src ./src

# Execute automated tests
CMD ["mvn", "clean", "install", "-Dheadless=true"]