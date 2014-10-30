package me.nithanim.netty.packetlib.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import me.nithanim.netty.packetlib.PacketProcessorRegistry;
import me.nithanim.netty.packetlib.packets.Packet;
import me.nithanim.netty.packetlib.processing.PacketProcessor;

public class PacketHandler extends ChannelHandlerAdapter {
    private Channel channel;
    private final PacketProcessorRegistry packetProcessorRegistry;

    public PacketHandler(PacketProcessorRegistry packetProcessorRegistry) {
        this.packetProcessorRegistry = packetProcessorRegistry;
    }
    
    public void writePacket(Packet packet) {
        channel.write(packet);
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Packet packet = (Packet) msg;
        PacketProcessor packetProcessor = packetProcessorRegistry.getPacketProcessorFor(packet);
        packet.processPacket(packetProcessor);
        
        ReferenceCountUtil.release(packet);
    }
    
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channel = ctx.channel();
    }

    public Channel getChannel() {
        return channel;
    }
}