package com.amos.arpc.netty.server;

import com.amos.arpc.netty.common.protocol.PacketCodec;
import com.amos.arpc.netty.common.protocol.PacketSplitter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NettyServer
 *
 * @author wangdaoyuan.wdy
 * @date 2021/6/20
 */
public class NettyServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    public static void startServer(String hostname, int port) {
        startServer0(hostname, port);
    }

    private static void startServer0(String hostname, int port) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            final ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new PacketSplitter());
                            pipeline.addLast(PacketCodec.getInstance());
                            pipeline.addLast(new NettyServerHandler());
                        }
                    });

            final ChannelFuture channelFuture = bootstrap.bind(hostname, port).sync();

            LOGGER.info("RPC Provider Running! host: [{}], port: [{}]", hostname, port);
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
