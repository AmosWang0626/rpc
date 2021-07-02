package com.amos.api.service;

import com.amos.api.model.CommonResponse;
import com.amos.api.model.RegisterRequest;

/**
 * HelloService
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2021/5/28
 */
public interface HelloService {

    /**
     * Say Hello
     *
     * @param name name
     * @return message
     */
    String sayHello(String name);

    /**
     * 注册（复杂参数验证）
     *
     * @param request 请求表单
     * @return 注册结果
     */
    CommonResponse<String> register(RegisterRequest request);

}
