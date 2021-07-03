package com.amos.arpc.netty.common.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 解决拆包/粘包
 *
 * @author Daoyuan
 * @date 2019/3/21
 */
public class PacketSplitter extends LengthFieldBasedFrameDecoder {

    /**
     * 魔数 + 版本号 + 序列化方式 + 指令类型
     * 4 + 1 + 1 + 1 = 7
     */
    private static final int PROTOCOL_OFFSET = 7;
    /**
     * 数据长度 4(int)
     */
    private static final int PROTOCOL_LENGTH = 4;

    public PacketSplitter() {
        super(Integer.MAX_VALUE, PROTOCOL_OFFSET, PROTOCOL_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (PacketProtocol.MAGIC_NUMBER != in.getInt(in.readerIndex())) {
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }

}
