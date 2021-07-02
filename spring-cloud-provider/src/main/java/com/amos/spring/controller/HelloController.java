package com.amos.spring.controller;

import com.amos.api.model.CommonResponse;
import com.amos.api.model.RegisterRequest;
import com.amos.api.service.HelloService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * HelloController
 *
 * @author wangdaoyuan.wdy
 * @date 2021/7/1
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @Resource
    private HelloService helloService;

    @GetMapping("{name}")
    public String sayHello(@PathVariable("name") String name) {

        return helloService.sayHello(name);
    }

    @PostMapping("register")
    public CommonResponse<String> register(@RequestBody RegisterRequest registerRequest) {

        return helloService.register(registerRequest);
    }

}
