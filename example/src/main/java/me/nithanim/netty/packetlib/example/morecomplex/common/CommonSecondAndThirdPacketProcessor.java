package me.nithanim.netty.packetlib.example.morecomplex.common;

import io.netty.channel.Channel;
import me.nithanim.netty.packetlib.processing.PacketProcessorBase;

public abstract class CommonSecondAndThirdPacketProcessor extends PacketProcessorBase implements CommonSecondPacketProcessor, CommonThirdPacketProcessor {
    public CommonSecondAndThirdPacketProcessor(Channel channel) {
        super(channel);
    }
}