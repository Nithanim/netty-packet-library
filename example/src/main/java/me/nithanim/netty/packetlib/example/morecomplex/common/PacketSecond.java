package me.nithanim.netty.packetlib.example.morecomplex.common;

import io.netty.buffer.ByteBuf;
import me.nithanim.netty.packetlib.packets.PacketBase;
import me.nithanim.netty.packetlib.util.StringUtil;

public class PacketSecond extends PacketBase<CommonSecondPacketProcessor> {
    private String string;
    
    public PacketSecond() {
    }

    public PacketSecond(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
    
    @Override
    public int getId() {
        return 2;
    }

    @Override
    public int getPayloadSize() {
        //bytesize of string + string content
        return Short.SIZE/8 + StringUtil.getUtf8Size(string);
    }

    @Override
    public void pack(ByteBuf buffer) {
        byte[] stringBytes = StringUtil.getUtf8Bytes(string);
        buffer.writeShort(stringBytes.length);
        buffer.writeBytes(stringBytes);
    }

    @Override
    public void unpack(ByteBuf buffer) {
        byte[] stringBytes = new byte[buffer.readShort()];
        buffer.readBytes(stringBytes);
        string = StringUtil.getUtf8String(stringBytes);
    }

    @Override
    public void processPacket(CommonSecondPacketProcessor packetProcessor) {
        packetProcessor.processSecondPacket(this);
    }
}
