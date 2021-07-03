package com.amos.arpc.netty.common.protocol;

import com.amos.arpc.netty.common.model.BaseModel;
import com.amos.arpc.netty.common.serializer.SerializerTypeEnum;
import com.amos.arpc.netty.common.model.ModelFactory;
import io.netty.buffer.ByteBuf;

/**
 * 通讯协议
 * (魔数 + 协议版本号 + 序列化方式 + 指令类型 + 数据包长度 + 数据包)
 * (int + byte  +   byte   +  byte  +   int    + byte[])
 *
 * @author Daoyuan
 * @date 2019/3/19
 */
public class PacketProtocol {

    /**
     * 魔数
     */
    public static final int MAGIC_NUMBER = 0x12345678;

    private static volatile PacketProtocol instance;

    /**
     * 序列化BasePacket并根据协议编码
     *
     * @param baseModel BasePacket
     * @return ByteBuf
     */
    public ByteBuf encode(ByteBuf byteBuf, BaseModel baseModel) {
        SerializerTypeEnum serializerType = SerializerTypeEnum.getOrDefault(baseModel.getSerializeType());

        // 序列化 Java 对象
        byte[] bytes = serializerType.getSerializer().serialize(baseModel);

        // 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(baseModel.getProtocolVersion());
        byteBuf.writeByte(serializerType.getType());
        byteBuf.writeByte(baseModel.getCommand().getType());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    /**
     * 根据协议解码并反序列化为BasePacket
     *
     * @param byteBuf ByteBuf
     * @return BasePacket
     */
    public BaseModel decode(ByteBuf byteBuf) {
        // 跳过 魔数
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化方式
        byte serializeType = byteBuf.readByte();
        // 指令类型
        byte commandType = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();
        // 数据包
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends BaseModel> requestClass = ModelFactory.getRequestClass(commandType);
        SerializerTypeEnum serializerType = SerializerTypeEnum.getOrDefault(serializeType);

        if (requestClass != null) {
            return serializerType.getSerializer().deserialize(requestClass, bytes);
        }

        return null;
    }

    public static PacketProtocol getInstance() {
        if (instance == null) {
            synchronized (PacketProtocol.class) {
                if (instance == null) {
                    instance = new PacketProtocol();
                }
            }
        }
        return instance;
    }

}
