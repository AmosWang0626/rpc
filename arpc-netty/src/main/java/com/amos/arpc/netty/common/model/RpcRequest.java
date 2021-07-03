package com.amos.arpc.netty.common.model;

import lombok.Getter;
import lombok.Setter;

/**
 * RpcRequest
 *
 * @author wangdaoyuan.wdy
 * @date 2021/6/20
 */
@Getter
@Setter
public class RpcRequest extends BaseModel {

    private static final long serialVersionUID = -3433216132915569977L;

    private String className;
    private String methodName;
    private String version;

    private Class<?>[] parameterTypes;
    private Object[] args;

    @Override
    public ModelTypeEnum getCommand() {
        return ModelTypeEnum.RPC_REQUEST;
    }
}
