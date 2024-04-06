FROM eclipse-temurin:17-jdk-focal AS BUILD

WORKDIR /usr/app/

COPY . . 

RUN chmod +x ./gradlew && ./gradlew assemble

FROM openjdk:latest

ENV JAR_NAME=sumotournamenthub-backend-0.0.1-SNAPSHOT.jar

ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

COPY --from=BUILD /usr/app/build/libs/$JAR_NAME $APP_HOME

EXPOSE 8080

ENTRYPOINT exec java -jar $JAR_NAME -web -webAllowOthers -tcp -tcpAllowOthers -browser
