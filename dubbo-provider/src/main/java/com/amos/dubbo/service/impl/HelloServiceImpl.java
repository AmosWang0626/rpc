package com.amos.dubbo.service.impl;

import com.amos.api.model.CommonResponse;
import com.amos.api.model.RegisterRequest;
import com.amos.api.service.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * HelloServiceImpl
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2021/5/28
 */
@DubboService(interfaceName = "helloService",version = "${provider.version}", interfaceClass = HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return String.format("Hello %s!", name);
    }

    @Override
    public CommonResponse<String> register(RegisterRequest request) {
        System.out.println(request);
        return new CommonResponse<>();
    }

}
