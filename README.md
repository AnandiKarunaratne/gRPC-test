# gRPC-hello

This is a sample tutorial for gRPC introduction given in https://www.baeldung.com/grpc-introduction

It has a simple client-server implementation, where client sends their name and server returns a hello message with the name.

## How to run
1. Clone the repository.
2. Generate the code by running `pom.xml` file.
3. Override the `hello()` implementation given in abstract class `HelloServiceImplbase` in `target/generated-sources/protobuf/grpc-java/org/anda/grpc/HelloServiceGrpc.java`

```
public class HelloServiceImpl extends HelloServiceImplBase {

    @Override
    public void hello(
      HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        String greeting = new StringBuilder()
          .append("Hello, ")
          .append(request.getFirstName())
          .append(" ")
          .append(request.getLastName())
          .toString();

        HelloResponse response = HelloResponse.newBuilder()
          .setGreeting(greeting)
          .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
```
4. Run `src/main/java/com/anda/grpc/server/GrpcServer.java`.
5. Run `src/main/java/com/anda/grpc/server/GrpcClient.java`.
