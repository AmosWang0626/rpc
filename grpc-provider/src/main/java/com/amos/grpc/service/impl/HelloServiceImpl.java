package com.amos.grpc.service.impl;

import com.amos.grpc.api.model.CommonResponse;
import com.amos.grpc.api.model.HelloRequest;
import com.amos.grpc.api.model.HelloResponse;
import com.amos.grpc.api.model.RegisterRequest;
import com.amos.grpc.api.service.HelloServiceGrpc;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello Service Impl
 *
 * @author wangdaoyuan.wdy
 * @date 2021/7/3
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        final String name = request.getName();
        LOGGER.info("HelloService#sayHello 接收到消息: " + name);

        String message = String.format("Hello %s, Welcome to grpc!", name);

        HelloResponse response = HelloResponse.getDefaultInstance().toBuilder()
                .setMessage(message)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void register(RegisterRequest request, StreamObserver<CommonResponse> responseObserver) {
        LOGGER.info("HelloService#register 接收到消息: " + request);

        final Any body = Any.getDefaultInstance().toBuilder()
                .setValue(ByteString.copyFromUtf8(request.getName()))
                .build();

        CommonResponse response = CommonResponse.getDefaultInstance().toBuilder()
                .setCode("200").setMessage("成功!").setBody(body)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void good(Empty request, StreamObserver<Any> responseObserver) {
        LOGGER.info("HelloService#good ......「{}」", request);

        final Any response = Any.getDefaultInstance().toBuilder()
                .setValue(ByteString.copyFromUtf8("雷猴"))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
