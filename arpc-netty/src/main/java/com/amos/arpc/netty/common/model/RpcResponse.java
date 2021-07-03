package com.amos.arpc.netty.common.model;

import com.amos.arpc.netty.common.exception.RpcError;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * RpcResponse
 *
 * @author wangdaoyuan.wdy
 * @date 2021/6/20
 */
@Getter
@Setter
@Accessors(chain = true)
public class RpcResponse extends BaseModel {

    private static final long serialVersionUID = -3102202953303850603L;

    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * 失败原因
     */
    private RpcError rpcError;

    /**
     * 响应
     */
    private Object body;

    public boolean successful() {
        return success != null && success;
    }

    @Override
    public ModelTypeEnum getCommand() {
        return ModelTypeEnum.RPC_RESPONSE;
    }
}
