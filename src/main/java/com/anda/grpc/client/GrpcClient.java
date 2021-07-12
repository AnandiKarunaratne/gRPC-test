package com.anda.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.anda.grpc.HelloRequest;
import org.anda.grpc.HelloResponse;
import org.anda.grpc.HelloServiceGrpc;

public class GrpcClient {
    public static void main(String[] args) {

        // gRPC provides a channel construct that abstracts out the underlying details.
        // like connection, connection pooling, load balancing, etc.
        // we have used plaintext without any encryption.
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        // create stub.
        // stub = primary way for clients to interact with the server.
        // auto-generated stubs have constructors for wrapping the channel.
        // blocking/synchronous stub = RPC call waits for the server to respond
        // and will either return a response or raise and exception.
        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        // making hello() RPC call.
        // pass the HelloRequest.
        // using auto-generated setters to set attributes.
        // get back the HelloResponse object returned.
        HelloResponse helloResponse = stub.hello(HelloRequest
                .newBuilder()
                .setFirstName("Anandi")
                .setLastName("Karunaratne")
                .build());

        System.out.println("Response received from server:\n" + helloResponse);

        channel.shutdown();
    }
}
