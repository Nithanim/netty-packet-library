package me.nithanim.netty.packetlib.example.morecomplex.common;

import io.netty.channel.Channel;
import me.nithanim.netty.packetlib.processing.PacketProcessorBase;

public abstract class CommonFirstPacketProcessor extends PacketProcessorBase {
    public CommonFirstPacketProcessor(Channel channel) {
        super(channel);
    }
    
    public abstract void processFirstPacket(PacketFirst packet);
}