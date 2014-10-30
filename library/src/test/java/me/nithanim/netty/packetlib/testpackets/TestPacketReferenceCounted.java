package me.nithanim.netty.packetlib.testpackets;

import io.netty.buffer.ByteBuf;
import me.nithanim.netty.packetlib.packets.ReferenceCountedPacketBase;
import me.nithanim.netty.packetlib.processing.PacketProcessor;

public class TestPacketReferenceCounted extends ReferenceCountedPacketBase<PacketProcessor> {
    @Override
    public int getId() {
        return 97;
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
    }

    @Override
    public int getPacketSize() {
        return Byte.SIZE/8 + Short.SIZE/8 + getPayloadSize();
    }

    @Override
    protected void deallocate() {
        //packet empty
    }
}
