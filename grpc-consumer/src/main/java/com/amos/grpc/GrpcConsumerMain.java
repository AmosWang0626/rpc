package com.amos.grpc;

import com.amos.grpc.api.model.CommonResponse;
import com.amos.grpc.api.model.HelloRequest;
import com.amos.grpc.api.model.HelloResponse;
import com.amos.grpc.api.model.RegisterRequest;
import com.amos.grpc.config.BaseClient;
import com.amos.grpc.config.HelloServiceClient;
import com.google.protobuf.Any;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Random;

/**
 * ConsumerApplication
 *
 * @author wangdaoyuan.wdy
 * @date 2021/7/3
 */
public class GrpcConsumerMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcConsumerMain.class);

    private static final int DEFAULT_PORT = 50051;

    public static void main(String[] args) {
        final BaseClient baseClient = new BaseClient("127.0.0.1", DEFAULT_PORT);
        final HelloServiceClient helloServiceClient = new HelloServiceClient(baseClient);
        LOGGER.info("客户端连接成功!");

        // 1
        final HelloResponse helloResponse = helloServiceClient.sayHello(
                HelloRequest.newBuilder()
                        .setName("AmosWang")
                        .build()
        );
        LOGGER.info("sayHello >>> [{}]", helloResponse.getMessage());

        // 2
        final HashMap<String, String> extInfo = new HashMap<>(4);
        extInfo.put("index", "123");
        extInfo.put("picture", "https://amos.wang");
        extInfo.put("description", "天高任鸟飞，海阔任鱼跃。");
        final CommonResponse commonResponse = helloServiceClient.register(
                RegisterRequest.newBuilder()
                        .setName("小方")
                        .setGender(new Random().nextBoolean() ? "男" : "女")
                        .setAge(new Random().nextInt(10) + 18)
                        .putAllExtInfo(extInfo)
                        .build()
        );
        LOGGER.info("register >>> code: [{}], message: [{}], body: [{}]",
                commonResponse.getCode(),
                commonResponse.getMessage(),
                commonResponse.getBody().getValue().toStringUtf8()
        );

        // 3
        final Any any = helloServiceClient.good();
        LOGGER.info("good >>> [{}]", any.getValue().toStringUtf8());

        LOGGER.info("············· Finish.");
    }

}
