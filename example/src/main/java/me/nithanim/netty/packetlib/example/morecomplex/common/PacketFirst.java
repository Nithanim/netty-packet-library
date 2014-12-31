package me.nithanim.netty.packetlib.example.morecomplex.common;

import io.netty.buffer.ByteBuf;
import me.nithanim.netty.packetlib.packets.PacketBase;

public class PacketFirst extends PacketBase<CommonFirstPacketProcessor> {
    private int someRandomInteger;
    private float someRandomFloat;
    
    public PacketFirst() {
    }

    public PacketFirst(int someRandomInteger, float someRandomFloat) {
        this.someRandomInteger = someRandomInteger;
        this.someRandomFloat = someRandomFloat;
    }

    public int getSomeRandomInteger() {
        return someRandomInteger;
    }
    
    public float getSomeRandomFloat() {
        return someRandomFloat;
    }
    
    @Override
    public int getId() {
        return 1;
    }

    @Override
    public int getPayloadSize() {
        return Integer.SIZE/8 + Float.SIZE/8;
    }

    @Override
    public void pack(ByteBuf buffer) {
        buffer.writeInt(someRandomInteger);
        buffer.writeFloat(someRandomFloat);
    }

    @Override
    public void unpack(ByteBuf buffer) {
        someRandomInteger = buffer.readInt();
        someRandomFloat = buffer.readFloat();
    }

    @Override
    public void processPacket(CommonFirstPacketProcessor packetProcessor) {
        packetProcessor.processFirstPacket(this);
    }
}
