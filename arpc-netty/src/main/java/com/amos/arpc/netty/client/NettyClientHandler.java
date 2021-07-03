package com.amos.arpc.netty.client;

import com.amos.arpc.netty.common.exception.RpcException;
import com.amos.arpc.netty.common.model.RpcRequest;
import com.amos.arpc.netty.common.model.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * NettyClientHandler
 *
 * @author wangdaoyuan.wdy
 * @date 2021/6/20
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<RpcResponse> implements Callable<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyClientHandler.class);

    private ChannelHandlerContext context;

    private RpcRequest rpcRequest;
    private RpcResponse rpcResponse;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.context = ctx;
    }

    @Override
    protected synchronized void channelRead0(ChannelHandlerContext channelHandlerContext, RpcResponse rpcResponse) throws Exception {
        this.rpcResponse = rpcResponse;

        if (rpcResponse.successful()) {
            LOGGER.debug("call method successful, response body: [{}]", rpcResponse.getBody());
        } else {
            LOGGER.debug("call method fail, error code: [{}], message: [{}]",
                    rpcResponse.getRpcError().getCode(), rpcResponse.getRpcError().getMessage());
        }

        // 唤醒 call 中的等待方法
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(rpcRequest);

        // 等待返回结果
        wait();

        if (rpcResponse.successful()) {
            return rpcResponse.getBody();
        }

        throw new RpcException(rpcResponse.getRpcError().getMessage());
    }

    public void setRpcRequest(RpcRequest rpcRequest) {
        this.rpcRequest = rpcRequest;
    }
}
