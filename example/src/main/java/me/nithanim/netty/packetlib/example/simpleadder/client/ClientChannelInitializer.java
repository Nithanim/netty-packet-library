package me.nithanim.netty.packetlib.example.simpleadder.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import me.nithanim.netty.packetlib.PacketProcessorRegistry;
import me.nithanim.netty.packetlib.PacketProcessorRegistryImpl;
import me.nithanim.netty.packetlib.PacketRegistry;
import me.nithanim.netty.packetlib.handler.PacketCodec;
import me.nithanim.netty.packetlib.handler.PacketHandler;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final PacketRegistry packetRegistry;

    public ClientChannelInitializer(PacketRegistry packetRegistry) {
        this.packetRegistry = packetRegistry;
    }
    
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        PacketProcessorRegistry packetProcessorRegistry = new PacketProcessorRegistryImpl();
        packetProcessorRegistry.registerPacketProcessor(new ClientNumberPacketProcessor(ch));
        
        ch.pipeline().addLast(new PacketCodec(packetRegistry));
        ch.pipeline().addLast(new PacketHandler(packetProcessorRegistry));
    }
}
