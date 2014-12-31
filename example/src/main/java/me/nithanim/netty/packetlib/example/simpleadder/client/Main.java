package me.nithanim.netty.packetlib.example.simpleadder.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;
import me.nithanim.netty.packetlib.example.simpleadder.common.PacketNumber;
import me.nithanim.netty.packetlib.PacketRegistry;
import me.nithanim.netty.packetlib.PacketRegistryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) throws Exception {
        new Main().run();
    }
    
    @Override
    public void run() {
        PacketRegistry packetRegistry = new PacketRegistryImpl();
        packetRegistry.registerPacket(PacketNumber.class);
        
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ClientChannelInitializer(packetRegistry));
            
            logger.info("Connectiong to server...");
            ChannelFuture f = b.connect("127.0.0.1", 9090).sync();
            f.addListener(new GenericFutureListener<ChannelFuture>() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    logger.info("Connected!");
                    Channel channel = future.channel();
                    sendRequest(channel);
                }
            });
            f.channel().closeFuture().sync();
        } catch (InterruptedException ex) {
            logger.error("An exception was thrown in the mainloop!", ex);
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
    
    private void sendRequest(Channel channel) {
        PacketNumber packet1 = new PacketNumber(5);
        PacketNumber packet2 = new PacketNumber(10);
        logger.info("Sending number " + packet1.getNumber());
        channel.write(packet1);
        logger.info("Sending number " + packet2.getNumber());
        channel.write(packet2);
        channel.flush();
    }
}