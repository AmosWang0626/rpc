package com.amos.spring.controller;

import com.amos.api.model.CommonResponse;
import com.amos.api.model.RegisterRequest;
import com.amos.spring.api.HelloFeignApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @Resource
    private HelloFeignApi helloFeignApi;

    @GetMapping("{name}")
    public String sayHello(@PathVariable("name") String name) {
        return helloFeignApi.sayHello(name);
    }

    @GetMapping("register")
    public CommonResponse<String> register() {
        final RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("小方");
        registerRequest.setGender(new Random().nextBoolean() ? "男" : "女");
        registerRequest.setAge(new Random().nextInt(10) + 18);

        final HashMap<String, String> extInfo = new HashMap<>(4);
        extInfo.put("index", "123");
        extInfo.put("picture", "https://amos.wang");
        extInfo.put("description", "天高任鸟飞，海阔任鱼跃。");

        registerRequest.setExtInfo(extInfo);

        return helloFeignApi.register(registerRequest);
    }

}
