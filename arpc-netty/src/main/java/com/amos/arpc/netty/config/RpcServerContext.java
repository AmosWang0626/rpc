package com.amos.arpc.netty.config;

import com.amos.arpc.netty.common.exception.RpcInitException;
import org.springframework.context.ApplicationContext;

/**
 * NettyRpcContext
 *
 * @author wangdaoyuan.wdy
 * @date 2021/6/20
 */
public class RpcServerContext {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        RpcServerContext.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            throw new RpcInitException("服务端初始化错误，请检查 NettyRpcContext.applicationContext 配置");
        }

        return applicationContext;
    }
}
