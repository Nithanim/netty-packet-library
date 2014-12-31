package me.nithanim.netty.packetlib.example.morecomplex.common;

import me.nithanim.netty.packetlib.processing.PacketProcessor;

public interface CommonThirdPacketProcessor extends PacketProcessor {
    public abstract void processThirdPacket(PacketThird packet);
}
