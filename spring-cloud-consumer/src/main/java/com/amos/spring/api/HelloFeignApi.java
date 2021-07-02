package com.amos.spring.api;

import com.amos.api.model.CommonResponse;
import com.amos.api.model.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * HelloFeignApi
 *
 * @author wangdaoyuan.wdy
 * @date 2021/7/2
 */
@FeignClient(name = "spring-cloud-provider", path = "hello")
public interface HelloFeignApi {

    @GetMapping("{name}")
    String sayHello(@PathVariable("name") String name);

    @PostMapping("register")
    CommonResponse<String> register(@RequestBody RegisterRequest registerRequest);

}
