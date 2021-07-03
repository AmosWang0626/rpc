package com.amos.grpc;

import com.amos.grpc.service.impl.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * ProviderApplication
 *
 * @author wangdaoyuan.wdy
 * @date 2021/7/3
 */
public class GrpcProviderMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcProviderMain.class);

    private Server server;

    private static final int DEFAULT_PORT = 50051;

    public static void main(String[] args) throws IOException, InterruptedException {
        final GrpcProviderMain server = new GrpcProviderMain();

        LOGGER.info("服务端启动成功!");
        server.startWithDaemon();
    }

    /**
     * 服务启动类
     */
    private void startWithDaemon() throws IOException, InterruptedException {
        server = ServerBuilder.forPort(DEFAULT_PORT)
                .addService(new HelloServiceImpl())
                .build()
                .start();

        // 守护进程
        server.awaitTermination();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info(">>>>>> JVM shutdown, and gRPC closing");
            this.shutdown();
            LOGGER.info(">>>>>> gRPC closed!");
        }));
    }

    /**
     * RPC 服务关闭
     */
    private void shutdown() {
        if (server != null) {
            server.shutdown();
        }
    }

}
