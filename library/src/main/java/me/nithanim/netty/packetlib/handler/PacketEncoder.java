package me.nithanim.netty.packetlib.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ReferenceCountUtil;
import me.nithanim.netty.packetlib.packets.Packet;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
        int packetSize = packet.getPayloadSize();
        out.writeByte(packet.getId());
        out.writeShort(packetSize);
        packet.pack(out);
        ReferenceCountUtil.release(packet);
    }
}
