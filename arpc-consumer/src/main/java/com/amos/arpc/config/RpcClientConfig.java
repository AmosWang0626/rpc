package com.amos.arpc.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * RpcClientConfig
 *
 * @author wangdaoyuan.wdy
 * @date 2021/6/20
 */
@Configuration
public class RpcClientConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }
}
