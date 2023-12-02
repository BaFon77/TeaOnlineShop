FROM maven:3.8.4 AS build
WORKDIR /app
COPY pom.xml .
COPY src src
RUN mvn package

FROM amazoncorretto:17.0.6
WORKDIR /app
COPY --from=build /app /app
COPY --from=build /app/target/shop-0.0.1-SNAPSHOT.jar /app/shop-0.0.1-SNAPSHOT.jar
#USER root
#RUN chown root:root /app/shop-0.0.1-SNAPSHOT.jar
#RUN chmod 777 /app/shop-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/shop-0.0.1-SNAPSHOT.jar"]