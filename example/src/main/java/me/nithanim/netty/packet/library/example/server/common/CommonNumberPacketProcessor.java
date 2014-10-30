package me.nithanim.netty.packet.library.example.server.common;

import io.netty.channel.Channel;
import me.nithanim.netty.packetlib.processing.PacketProcessorBase;

public abstract class CommonNumberPacketProcessor extends PacketProcessorBase {
    public CommonNumberPacketProcessor(Channel channel) {
        super(channel);
    }
    
    public abstract void processNumberPacket(PacketNumber packet);
}