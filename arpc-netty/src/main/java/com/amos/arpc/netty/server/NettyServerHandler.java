package com.amos.arpc.netty.server;

import com.alibaba.fastjson.JSON;
import com.amos.arpc.netty.common.exception.RpcErrorEnum;
import com.amos.arpc.netty.common.model.RpcRequest;
import com.amos.arpc.netty.common.model.RpcResponse;
import com.amos.arpc.netty.config.RpcServerContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * NettyServerHandler
 *
 * @author wangdaoyuan.wdy
 * @date 2021/6/20
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest rpcRequest) throws Exception {
        final RpcResponse rpcResponse = new RpcResponse().setSuccess(false);

        Object bean;
        try {
            bean = RpcServerContext.getApplicationContext().getBean(Class.forName(rpcRequest.getClassName()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

            rpcResponse.setRpcError(RpcErrorEnum.RPC_SERVICE_NOT_FOUND);
            ctx.writeAndFlush(rpcResponse);
            return;
        }

        Method method;
        try {
            method = bean.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();

            rpcResponse.setRpcError(RpcErrorEnum.RPC_METHOD_NOT_FOUND);
            ctx.writeAndFlush(rpcResponse);
            return;
        }

        Object response;
        try {
            final Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
            Object[] finalArgs = new Object[parameterTypes.length];
            for (int i = 0, len = parameterTypes.length; i < len; i++) {
                final Object arg = rpcRequest.getArgs()[i];
                // 请求入参先转JSON，再序列化为指定类型对象
                finalArgs[i] = JSON.parseObject(JSON.toJSONString(arg), parameterTypes[i]);
            }
            response = method.invoke(bean, finalArgs);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();

            rpcResponse.setRpcError(RpcErrorEnum.RPC_CALL_METHOD_ERROR);
            ctx.writeAndFlush(rpcResponse);
            return;
        }

        rpcResponse.setSuccess(true).setBody(response);

        ctx.writeAndFlush(rpcResponse);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
