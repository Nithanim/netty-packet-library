package me.nithanim.netty.packetlib.testpackets;

import io.netty.buffer.ByteBuf;
import me.nithanim.netty.packetlib.packets.PacketBase;

public class TestPacketFull extends PacketBase<TestPacketProcessorFull> {
    
    long somePayload;
    int somePayload2;

    public TestPacketFull() {
    }

    public TestPacketFull(long somePayload, int somePayload2) {
        this.somePayload = somePayload;
        this.somePayload2 = somePayload2;
    }
    
    @Override
    public int getId() {
        return 99;
    }
    
    @Override
    public int getPayloadSize() {
        return Long.SIZE/8 + Integer.SIZE/8;
    }

    @Override
    public void pack(ByteBuf buffer) {
        buffer.writeLong(somePayload);
        buffer.writeInt(somePayload2);
    }

    @Override
    public void unpack(ByteBuf buffer) {
        somePayload = buffer.readLong();
        somePayload2 = buffer.readInt();
    }

    @Override
    public void processPacket(TestPacketProcessorFull packetProcessor) {
        packetProcessor.processPacketTestPacket(this);
    }
}
