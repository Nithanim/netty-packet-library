package me.nithanim.netty.packetlib.example.morecomplex.common;

import io.netty.buffer.ByteBuf;
import me.nithanim.netty.packetlib.packets.PacketBase;

public class PacketThird extends PacketBase<CommonThirdPacketProcessor> {
    public PacketThird() {
    }

    @Override
    public int getId() {
        return 3;
    }

    @Override
    public int getPayloadSize() {
        return 0;
    }

    @Override
    public void pack(ByteBuf buffer) {
    }

    @Override
    public void unpack(ByteBuf buffer) {
    }

    @Override
    public void processPacket(CommonThirdPacketProcessor packetProcessor) {
        packetProcessor.processThirdPacket(this);
    }
}
