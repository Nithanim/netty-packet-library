package me.nithanim.netty.packetlib.testpackets;

import io.netty.buffer.ByteBuf;
import me.nithanim.netty.packetlib.packets.PacketBase;

public class TestPacketEmpty extends PacketBase<TestPacketProcessorEmpty>{
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
    public void processPacket(TestPacketProcessorEmpty packetProcessor) {
        packetProcessor.processPacketTestPacketEmpty(this);
    }
}
