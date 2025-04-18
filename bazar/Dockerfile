FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/bazar-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_bazar.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app_bazar.jar"]
