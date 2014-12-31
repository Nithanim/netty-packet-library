package me.nithanim.netty.packetlib.example.simpleadder.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import me.nithanim.netty.packetlib.PacketProcessorRegistry;
import me.nithanim.netty.packetlib.PacketProcessorRegistryImpl;
import me.nithanim.netty.packetlib.PacketRegistry;
import me.nithanim.netty.packetlib.handler.PacketCodec;
import me.nithanim.netty.packetlib.handler.PacketHandler;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final PacketRegistry packetRegistry;

    public ServerChannelInitializer(PacketRegistry packetRegistry) {
        this.packetRegistry = packetRegistry;
    }
    
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        PacketProcessorRegistry packetProcessorRegistry = new PacketProcessorRegistryImpl();
        packetProcessorRegistry.registerPacketProcessor(new ServerFirstPacketProcessor(ch));
        
        ch.pipeline().addLast(new PacketCodec(packetRegistry));
        ch.pipeline().addLast(new PacketHandler(packetProcessorRegistry));
    }
}
