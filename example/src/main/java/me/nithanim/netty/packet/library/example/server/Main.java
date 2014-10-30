package me.nithanim.netty.packet.library.example.server;

import io.netty.bootstrap.ServerBootstrap;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import me.nithanim.netty.packet.library.example.server.common.PacketNumber;
import me.nithanim.netty.packetlib.PacketRegistry;
import me.nithanim.netty.packetlib.PacketRegistryImpl;

public class Main {
    public static void main(String[] args) throws Exception {
        new Main(9090).run();
    }
    
    private final int port;

    public Main(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        PacketRegistry packetRegistry = new PacketRegistryImpl();
        packetRegistry.registerPacket(PacketNumber.class);
        
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .childHandler(new ExampleChannelInitializer(packetRegistry))
             .option(ChannelOption.SO_BACKLOG, 128)
             .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}