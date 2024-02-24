FROM openjdk:17-alpine

WORKDIR /app

COPY build/libs/TokenValidation-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8082

ENV SPRING_DATA_MONGODB_URI="mongodb://localhost:27017/SecurityManagement"
ENV SERVER_PORT="8082"
ENV JAVA_OPTS="-XX:+UseContainerSupport --add-exports=java.base/sun.nio.ch=ALL-UNNAMED"

CMD ["java", "-jar", "-Dspring.data.mongodb.uri=${SPRING_DATA_MONGODB_URI}", "-Dserver.port=${SERVER_PORT}", "TokenValidation-0.0.1-SNAPSHOT.jar"]
