package me.nithanim.netty.packetlib.testpackets;

import io.netty.buffer.ByteBuf;
import me.nithanim.netty.packetlib.packets.PacketBase;
import me.nithanim.netty.packetlib.processing.PacketProcessor;

public class TestPacketWithoutDefaultConstructor extends PacketBase<PacketProcessor>{
    public TestPacketWithoutDefaultConstructor(int dummy) {
    }
    
    @Override
    public int getId() {
        return 98;
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
