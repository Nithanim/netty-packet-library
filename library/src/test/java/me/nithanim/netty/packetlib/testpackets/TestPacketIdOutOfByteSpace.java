package me.nithanim.netty.packetlib.testpackets;

import io.netty.buffer.ByteBuf;
import me.nithanim.netty.packetlib.packets.PacketBase;
import me.nithanim.netty.packetlib.processing.PacketProcessor;

public class TestPacketIdOutOfByteSpace extends PacketBase<PacketProcessor>{

    @Override
    public int getId() {
        return Byte.MAX_VALUE + 1;
    }

    @Override
    public int getPayloadSize() {
        return 0;
    }

    @Override
    public void pack(ByteBuf buffer) {
        //packet empty
    }

    @Override
    public void unpack(ByteBuf buffer) {
        //packet empty
    }

    @Override
    public void processPacket(PacketProcessor packetProcessor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
