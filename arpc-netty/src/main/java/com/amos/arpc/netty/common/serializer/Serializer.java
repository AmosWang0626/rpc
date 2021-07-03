package com.amos.arpc.netty.common.serializer;

/**
 * PROJECT: im
 * DESCRIPTION: 序列化接口
 *
 * @author Daoyuan
 * @date 2019/3/19
 */
public interface Serializer {

    /**
     * 序列化算法
     *
     * @return serializer code
     */
    byte getSerializerCode();

    /**
     * java 对象转换成二进制
     *
     * @param object java object
     * @return byte object
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     *
     * @param clazz java object class
     * @param bytes byte object
     * @return java object
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
