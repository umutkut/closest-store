FROM openjdk:11
ARG JAR_FILE=target/*.jar
ARG JSON_FILE=target/classes/data/*.json
COPY ${JAR_FILE} app.jar
COPY ${JSON_FILE} data/stores.json
ENTRYPOINT ["java","-jar","/app.jar"]