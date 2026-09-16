# 1. Gradle 빌드 단계
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Gradle 설정 파일 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x ./gradlew

COPY src src

# Spring Boot 빌드
RUN ./gradlew bootJar -x test


# 2. Spring Boot 실행 단계
FROM eclipse-temurin:21-jre

WORKDIR /app

# 빌드된 JAR 복사
COPY --from=0 /app/build/libs/*.jar app.jar

# Spring Boot 기본 포트
EXPOSE 8080

# Spring Boot 실행
ENTRYPOINT ["java", "-jar", "app.jar"]