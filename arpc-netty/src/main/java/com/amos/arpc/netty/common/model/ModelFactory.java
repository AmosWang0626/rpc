package com.amos.arpc.netty.common.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ModelFactory
 *
 * @author wangdaoyuan.wdy
 * @date 2021/6/20
 */
public class ModelFactory {

    private static final Map<ModelTypeEnum, Class<? extends BaseModel>> PACKET_MAP = new ConcurrentHashMap<>();

    static {
        PACKET_MAP.put(ModelTypeEnum.RPC_REQUEST, RpcRequest.class);
        PACKET_MAP.put(ModelTypeEnum.RPC_RESPONSE, RpcResponse.class);
    }

    /**
     * 根据命令获取类
     *
     * @param command 命令
     * @return class
     */
    public static Class<? extends BaseModel> getRequestClass(byte command) {
        final ModelTypeEnum modelTypeEnum = ModelTypeEnum.valueOf(command);
        if (modelTypeEnum == null) {
            return null;
        }

        return PACKET_MAP.get(modelTypeEnum);
    }

}
