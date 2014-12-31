package me.nithanim.netty.packetlib.example.morecomplex.server;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import io.netty.bootstrap.ServerBootstrap;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import me.nithanim.netty.packetlib.PacketRegistry;
import me.nithanim.netty.packetlib.PacketRegistryImpl;
import me.nithanim.netty.packetlib.example.morecomplex.common.PacketFirst;
import me.nithanim.netty.packetlib.example.morecomplex.common.PacketSecond;
import me.nithanim.netty.packetlib.example.morecomplex.common.PacketThird;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) throws Exception {
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);
        
        new Main(9090).run();
    }
    
    private final int port;

    public Main(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        logger.info("Setting up server...");
        PacketRegistry packetRegistry = new PacketRegistryImpl();
        packetRegistry.registerPacket(PacketFirst.class);
        packetRegistry.registerPacket(PacketSecond.class);
        packetRegistry.registerPacket(PacketThird.class);
        
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer(packetRegistry))
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
            
            logger.info("Waiting for incoming connections...");
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException ex) {
            logger.error("An exception was thrown in the mainloop!", ex);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}