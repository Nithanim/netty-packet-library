package me.nithanim.netty.packetlib.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import java.util.List;
import me.nithanim.netty.packetlib.PacketRegistry;
import me.nithanim.netty.packetlib.packets.Packet;

public class PacketCodec extends ByteToMessageCodec<Packet> {
    private final PacketEncoder encoder;
    private final PacketDecoder decoder;
    
    public PacketCodec(PacketRegistry packetRegistry) {
        this.encoder = new PacketEncoder();
        this.decoder = new PacketDecoder(packetRegistry);
    }
    
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        encoder.encode(ctx, msg, out);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        decoder.decode(ctx, in, out);
    }
    
}
