package com.amos.arpc.netty.common.exception;

/**
 * RpcException
 *
 * @author wangdaoyuan.wdy
 * @date 2021/6/20
 */
public class RpcException extends RuntimeException {

    private static final long serialVersionUID = 6716202271263869284L;

    public RpcException() {
        super("RPC 调用异常，请检查请求参数");
    }

    public RpcException(String message) {
        super(message);
    }

}
