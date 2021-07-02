package com.amos.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EchoController
 *
 * @author wangdaoyuan.wdy
 * @date 2021/7/1
 */
@RestController
public class EchoController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("ping")
    public String sayHello() {
        return "pong-" + port;
    }

}
