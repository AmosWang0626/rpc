package com.amos.arpc.controller;

import com.amos.api.model.CommonResponse;
import com.amos.api.model.RegisterRequest;
import com.amos.api.service.HelloService;
import com.amos.arpc.netty.client.NettyClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Random;

/**
 * HelloController
 *
 * @author wangdaoyuan.wdy
 * @date 2021/7/2
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @Value("${arpc.host}")
    private String host;
    @Value("${arpc.port}")
    private Integer port;

    private NettyClient nettyClient;

    @GetMapping("{name}")
    public String sayHello(@PathVariable("name") String name) {
        final HelloService helloService = getInstance().getBean(HelloService.class);

        return helloService.sayHello(name);
    }

    @GetMapping("register")
    public CommonResponse<String> register() {
        final HelloService helloService = getInstance().getBean(HelloService.class);

        final RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("小方");
        registerRequest.setGender(new Random().nextBoolean() ? "男" : "女");
        registerRequest.setAge(new Random().nextInt(10) + 18);

        final HashMap<String, String> extInfo = new HashMap<>(4);
        extInfo.put("index", "123");
        extInfo.put("picture", "https://amos.wang");
        extInfo.put("description", "天高任鸟飞，海阔任鱼跃。");
        registerRequest.setExtInfo(extInfo);

        return helloService.register(registerRequest);
    }

    private NettyClient getInstance() {
        if (nettyClient == null) {
            synchronized (NettyClient.class) {
                if (nettyClient == null) {
                    nettyClient = new NettyClient(host, port);
                }
            }
        }

        return nettyClient;
    }

}
