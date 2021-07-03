package com.amos.grpc.config;

import com.amos.grpc.api.service.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * BaseClient
 *
 * @author wangdaoyuan.wdy
 * @date 2021/7/3
 */
public class BaseClient {

    private final ManagedChannel channel;
    private final HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub;

    /**
     * 构造客户端与Greeter 服务端连接
     *
     * @param host 主机地址
     * @param port 端口
     */
    public BaseClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext().build());
    }

    private BaseClient(ManagedChannel channel) {
        this.channel = channel;
        this.helloServiceBlockingStub = HelloServiceGrpc.newBlockingStub(channel);
    }

    /**
     * 关闭函数
     *
     * @throws InterruptedException 延时退出
     */
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public HelloServiceGrpc.HelloServiceBlockingStub getHelloServiceBlockingStub() {
        return helloServiceBlockingStub;
    }
}
