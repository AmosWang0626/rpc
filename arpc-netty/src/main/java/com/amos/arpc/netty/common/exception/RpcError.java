package com.amos.arpc.netty.common.exception;

/**
 * RpcError
 *
 * @author wangdaoyuan.wdy
 * @date 2021/6/20
 */
public interface RpcError {

    /**
     * 获取 code
     *
     * @return code
     */
    String getCode();

    /**
     * 获取 message
     *
     * @return message
     */
    String getMessage();

}
