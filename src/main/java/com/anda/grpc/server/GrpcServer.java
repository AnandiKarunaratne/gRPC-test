package com.anda.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.anda.grpc.HelloRequest;
import org.anda.grpc.HelloResponse;
import org.anda.grpc.HelloServiceGrpc;

import java.io.IOException;

// gRPC server = listens to incoming requests
public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {

        // create server using builder on port 8080
        // add HelloServiceImpl service we defined
        Server server = ServerBuilder
                .forPort(9090)
                .addService(new HelloServiceImpl())
                .build();

        // starts the server
        server.start();
        System.out.println("Server listening...");
        // keeps the server running in the foreground blocking the prompt
        server.awaitTermination();
    }


    //  the default implementation of HelloServiceImplBase throws runtime exception of method unimplemented
//  extend HelloServiceImplBase and override hello() method
    public static class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

        //    comparing with HelloService.proto
//    this does not return HelloResponse
//    instead takes the 2nd argument as StreamObserver<HelloResponse>
//    it is a response observer - a callback for the server to call with its response
//    so client gets an option to make a blocking/non-blocking call
        @Override
        public void hello(
                HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

            String greeting = new StringBuilder()
                    .append("Hello, ")
                    .append(request.getFirstName())
                    .append(" ")
                    .append(request.getLastName())
                    .toString();

//      gRPC uses builders for creating objects
            HelloResponse response = HelloResponse.newBuilder()
                    .setGreeting(greeting)
                    .build();
//      to send to client
            responseObserver.onNext(response);
//      to specify that we have finished dealing with the RPC
//      else connection will be hung, and client will wait for more information
            responseObserver.onCompleted();
        }
    }
}
