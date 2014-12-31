package me.nithanim.netty.packetlib.example.morecomplex.common;

import me.nithanim.netty.packetlib.processing.PacketProcessor;

public interface CommonSecondPacketProcessor extends PacketProcessor {
    public abstract void processSecondPacket(PacketSecond packet);
}
