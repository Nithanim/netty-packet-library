package me.nithanim.netty.packet.library.example.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;
import me.nithanim.netty.packet.library.example.server.common.PacketNumber;
import me.nithanim.netty.packetlib.PacketRegistry;
import me.nithanim.netty.packetlib.PacketRegistryImpl;
import me.nithanim.netty.packetlib.packets.Packet;

public class Main {
    public static void main(String[] args) throws Exception {
        new Main().run();
    }
    
    public void run() throws Exception {
        PacketRegistry packetRegistry = new PacketRegistryImpl();
        packetRegistry.registerPacket(PacketNumber.class);
        
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ExampleChannelInitializer(packetRegistry));

            ChannelFuture f = b.connect("127.0.0.1", 9090).sync();
            f.addListener(new GenericFutureListener<ChannelFuture>() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    Channel channel = future.channel();
                    sendRequest(channel);
                }
            });
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
    
    private void sendRequest(Channel channel) {
        PacketNumber packet1 = new PacketNumber(5);
        PacketNumber packet2 = new PacketNumber(10);
        System.out.println("Sending number " + packet1.getNumber());
        System.out.println("Sending number " + packet2.getNumber());
        channel.write(packet1);
        channel.write(packet2);
        channel.flush();
    }
}