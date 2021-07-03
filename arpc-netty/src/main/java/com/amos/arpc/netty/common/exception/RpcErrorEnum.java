package com.amos.arpc.netty.common.exception;

/**
 * RpcErrorEnum
 *
 * @author wangdaoyuan.wdy
 * @date 2021/6/20
 */
public enum RpcErrorEnum implements RpcError {
    /**
     * 错误码，错误描述
     */
    RPC_SERVICE_NOT_FOUND("100401", "Service Not Found"),
    RPC_METHOD_NOT_FOUND("100402", "Method Not Found"),
    RPC_CALL_METHOD_ERROR("100403", "Call Method Error");

    private final String code;

    private final String message;

    RpcErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "RpcErrorEnum{" + "code='" + code + '\'' + ", message='" + message + '\'' + '}';
    }
}
