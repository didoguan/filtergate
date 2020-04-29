package com.deepspc.filtergate.modular.nettyclient.core;

import com.deepspc.filtergate.config.properties.NettyProperties;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description Netty客户端服务
 * @Author didoguan
 * @Date 2020/4/26
 **/
@Component
@Slf4j
public class NettyClient {
    @Autowired
    private NettyProperties nettyProperties;

    private ChannelFuture future;

    public NettyClient() {

    }

    public void startAndSend(Object sendData) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap().group(group)
                .option(ChannelOption.TCP_NODELAY, true)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());
        try {
            future = bootstrap.connect(nettyProperties.getHost(), nettyProperties.getPort()).sync();
            //向服务端发送数据
            future.channel().writeAndFlush(sendData);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            group.shutdownGracefully();
        }
    }
}
