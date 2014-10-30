package me.nithanim.netty.packet.library.example.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import me.nithanim.netty.packetlib.PacketProcessorRegistry;
import me.nithanim.netty.packetlib.PacketProcessorRegistryImpl;
import me.nithanim.netty.packetlib.PacketRegistry;
import me.nithanim.netty.packetlib.handler.PacketCodec;
import me.nithanim.netty.packetlib.handler.PacketHandler;

public class ExampleChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final PacketRegistry packetRegistry;

    public ExampleChannelInitializer(PacketRegistry packetRegistry) {
        this.packetRegistry = packetRegistry;
    }
    
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        PacketProcessorRegistry packetProcessorRegistry = new PacketProcessorRegistryImpl();
        packetProcessorRegistry.registerPacketProcessor(new ServerNumberPacketProcessor(ch));
        
        ch.pipeline().addLast(new PacketCodec(packetRegistry));
        ch.pipeline().addLast(new PacketHandler(packetProcessorRegistry));
    }
}
