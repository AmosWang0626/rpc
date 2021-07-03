package com.amos.arpc.config;

import com.amos.arpc.netty.config.RpcServerContext;
import com.amos.arpc.netty.server.NettyServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.annotation.Resource;

/**
 * RpcServerConfig
 *
 * @author wangdaoyuan.wdy
 * @date 2021/6/20
 */
@Configuration
public class RpcServerConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private ApplicationContext applicationContext;

    @Value("${arpc.host}")
    private String host;
    @Value("${arpc.port}")
    private Integer port;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        RpcServerContext.setApplicationContext(applicationContext);

        NettyServer.startServer(host, port);
    }
}
