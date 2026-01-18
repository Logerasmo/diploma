FROM azul/zulu-openjdk-alpine:17-latest

EXPOSE 8800

COPY target/diploma-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]