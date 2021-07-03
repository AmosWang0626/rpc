package com.amos.arpc.netty.common.exception;

/**
 * RpcInitException
 *
 * @author wangdaoyuan.wdy
 * @date 2021/6/20
 */
public class RpcInitException extends RuntimeException {

    private static final long serialVersionUID = -5186006288777683248L;

    public RpcInitException() {
        super("RPC 初始化异常，请检查配置信息");
    }

    public RpcInitException(String message) {
        super(message);
    }

}
