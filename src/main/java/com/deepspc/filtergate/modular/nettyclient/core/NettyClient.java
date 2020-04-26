package com.deepspc.filtergate.modular.nettyclient.core;

import com.deepspc.filtergate.config.properties.NettyProperties;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @Description Netty客户端服务
 * @Author didoguan
 * @Date 2020/4/26
 **/
@Component
public class NettyClient {
    @Autowired
    private NettyProperties nettyProperties;

    CountDownLatch countDown = new CountDownLatch(1);

    private ChannelFuture future;

    public NettyClient() {

    }

    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap().group(group)
                .option(ChannelOption.TCP_NODELAY, true)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());
        try {
            future = bootstrap.connect(nettyProperties.getHost(), nettyProperties.getPort()).sync();
            //用来表示客户端连接加载完毕
            countDown.countDown();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            group.shutdownGracefully();
        }
    }

    /**
     * 向Netty发送数据
     * @param msg
     */
    public void send(Object msg) {
        try {
            countDown.await();
            future.channel().writeAndFlush(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
