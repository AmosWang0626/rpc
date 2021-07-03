package com.amos.grpc.config;

import com.amos.grpc.api.model.CommonResponse;
import com.amos.grpc.api.model.HelloRequest;
import com.amos.grpc.api.model.HelloResponse;
import com.amos.grpc.api.model.RegisterRequest;
import com.amos.grpc.api.service.HelloServiceGrpc;
import com.google.protobuf.Any;
import com.google.protobuf.Empty;

/**
 * HelloServiceClient
 *
 * @author wangdaoyuan.wdy
 * @date 2021/7/3
 */
public class HelloServiceClient {

    private final HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub;

    public HelloServiceClient(BaseClient client) {
        helloServiceBlockingStub = client.getHelloServiceBlockingStub();
    }

    public HelloResponse sayHello(HelloRequest request) {

        return helloServiceBlockingStub.sayHello(request);
    }

    public CommonResponse register(RegisterRequest request) {

        return helloServiceBlockingStub.register(request);
    }

    public Any good() {

        return helloServiceBlockingStub.good(Empty.newBuilder().build());
    }

}
