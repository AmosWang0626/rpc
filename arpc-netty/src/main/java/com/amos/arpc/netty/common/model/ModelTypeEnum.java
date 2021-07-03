package com.amos.arpc.netty.common.model;

/**
 * 指令类型
 *
 * @author wangdaoyuan.wdy
 * @date 2021/6/20
 */
public enum ModelTypeEnum {

    /**
     * command
     */
    RPC_REQUEST((byte) 1),
    RPC_RESPONSE((byte) 2);

    private final byte type;

    ModelTypeEnum(byte type) {
        this.type = type;
    }

    public static ModelTypeEnum valueOf(byte type) {
        for (ModelTypeEnum value : ModelTypeEnum.values()) {
            if (value.type == type) {
                return value;
            }
        }

        return null;
    }

    public byte getType() {
        return type;
    }
}
