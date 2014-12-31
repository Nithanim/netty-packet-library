package me.nithanim.netty.packetlib.example.morecomplex.client;

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
        packetProcessorRegistry.registerPacketProcessor(new ClientFirstPacketProcessor(ch));
        packetProcessorRegistry.registerPacketProcessor(new ClientSecondPacketProcessor(ch));
        packetProcessorRegistry.registerPacketProcessor(new ClientThirdPacketProcessor(ch));
        
        ch.pipeline().addLast(new PacketCodec(packetRegistry));
        ch.pipeline().addLast(new ClientDebugHandler()); //Prints Packets received and sent
        ch.pipeline().addLast(new PacketHandler(packetProcessorRegistry));
    }
}
