FROM openjdk:11
EXPOSE 8080
ADD /target/gRPC-test-1.0-SNAPSHOT-server-jar-with-dependencies.jar server.jar
ADD /target/gRPC-test-1.0-SNAPSHOT-client-jar-with-dependencies.jar client.jar
ENTRYPOINT java -jar server.jar  & java -jar client.jar
