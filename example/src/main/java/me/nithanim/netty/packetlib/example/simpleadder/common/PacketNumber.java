package me.nithanim.netty.packetlib.example.simpleadder.common;

import io.netty.buffer.ByteBuf;
import me.nithanim.netty.packetlib.packets.PacketBase;

public class PacketNumber extends PacketBase<CommonNumberPacketProcessor> {
    private int number;

    public PacketNumber() {
    }

    public PacketNumber(int number) {
        this.number = number;
    }
    
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getPayloadSize() {
        return Integer.SIZE/8;
    }

    @Override
    public void pack(ByteBuf buffer) {
        buffer.writeInt(number);
    }

    @Override
    public void unpack(ByteBuf buffer) {
        number = buffer.readInt();
    }

    @Override
    public void processPacket(CommonNumberPacketProcessor packetProcessor) {
        packetProcessor.processNumberPacket(this);
    }
}
