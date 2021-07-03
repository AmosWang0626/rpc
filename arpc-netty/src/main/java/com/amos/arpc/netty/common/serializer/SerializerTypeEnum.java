package com.amos.arpc.netty.common.serializer;

/**
 * 序列化算法
 *
 * @author Daoyuan
 * @date 2019/3/19
 */
public enum SerializerTypeEnum {

    /**
     * json
     */
    JSON((byte) 1, new Hessian2Serializer()),
    /**
     * dubbo hession2
     */
    HESSIAN2((byte) 2, new Hessian2Serializer()),
    /**
     * kryo 
     */
    KRYO((byte) 3, new KryoSerializer());

    private final byte type;
    private final Serializer serializer;

    SerializerTypeEnum(byte type, Serializer serializer) {
        this.type = type;
        this.serializer = serializer;
    }

    public static SerializerTypeEnum getOrDefault(Byte code) {
        if (code == null) {
            return HESSIAN2;
        }

        for (SerializerTypeEnum value : values()) {
            if (value.type == code) {
                return value;
            }
        }

        return HESSIAN2;
    }

    public byte getType() {
        return type;
    }

    public Serializer getSerializer() {
        return serializer;
    }
}
