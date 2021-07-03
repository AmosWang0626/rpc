package com.amos.arpc.netty.client;

import com.amos.arpc.netty.common.exception.RpcException;
import com.amos.arpc.netty.common.model.RpcRequest;
import com.amos.arpc.netty.common.protocol.PacketCodec;
import com.amos.arpc.netty.common.protocol.PacketSplitter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * NettyClient
 *
 * @author wangdaoyuan.wdy
 * @date 2021/6/20
 */
@SuppressWarnings(value = "all")
public class NettyClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyClient.class);

    private final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static NettyClientHandler clientHandler;

    private final String host;

    private final int port;

    private final int timeout;

    private final String version;

    public NettyClient(String host, int port) {
        this(host, port, "1.0.0", 5000);
    }

    public NettyClient(String host, int port, String version, int timeout) {
        this.host = host;
        this.port = port;
        this.version = version;
        this.timeout = timeout;
    }

    public <T> T getBean(Class<?> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, (proxy, method, args) -> {
            if ("equals".equals(method.getName())
                    || "hashCode".equals(method.getName())
                    || "toString".equals(method.getName())) {
                throw new RpcException("[equals/hashCode/toString] Method does not support RPC");
            }

            if (clientHandler == null) {
                initClient();
            }

            RpcRequest rpcRequest = new RpcRequest();
            rpcRequest.setClassName(clazz.getName());
            rpcRequest.setMethodName(method.getName());
            rpcRequest.setVersion(version);
            rpcRequest.setParameterTypes(method.getParameterTypes());
            rpcRequest.setArgs(args);

            clientHandler.setRpcRequest(rpcRequest);

            final long startTime = System.currentTimeMillis();
            final Object o = executor.submit(clientHandler).get();

            LOGGER.debug("RT [{}], ClassName: [{}], MethodName: [{}], Version: [{}]",
                    (System.currentTimeMillis() - startTime), clazz.getName(), method.getName(), version);

            return o;
        });
    }

    public void initClient() {
        clientHandler = new NettyClientHandler();

        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        final ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new PacketSplitter());
                        pipeline.addLast(PacketCodec.getInstance());
                        pipeline.addLast(clientHandler);
                    }
                });

        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout);

        try {
            bootstrap.connect(host, port).sync();
            LOGGER.info("RPC Consumer Running! host: [{}], port: [{}]", host, port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NettyClient that = (NettyClient) o;
        return port == that.port && host.equals(that.host) && version.equals(that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port, version);
    }
}
