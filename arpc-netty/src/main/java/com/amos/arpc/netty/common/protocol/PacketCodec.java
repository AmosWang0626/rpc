package com.amos.arpc.netty.common.protocol;

import com.amos.arpc.netty.common.model.BaseModel;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * 编码和解码
 *
 * @author amos
 * @date 2019/4/4
 */
@ChannelHandler.Sharable
public class PacketCodec extends MessageToMessageCodec<ByteBuf, BaseModel> {

    private static volatile PacketCodec instance;

    @Override
    protected void encode(ChannelHandlerContext ctx, BaseModel baseModel, List<Object> out) throws Exception {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        PacketProtocol.getInstance().encode(byteBuf, baseModel);
        out.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        out.add(PacketProtocol.getInstance().decode(msg));
    }

    public static PacketCodec getInstance() {
        if (instance == null) {
            synchronized (PacketCodec.class) {
                if (instance == null) {
                    instance = new PacketCodec();
                }
            }
        }
        return instance;
    }

}
