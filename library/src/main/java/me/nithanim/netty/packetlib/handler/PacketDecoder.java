package me.nithanim.netty.packetlib.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import me.nithanim.netty.packetlib.PacketException;
import me.nithanim.netty.packetlib.PacketRegistry;
import me.nithanim.netty.packetlib.packets.Packet;

public class PacketDecoder extends ByteToMessageDecoder {
    private final PacketRegistry packetRegistry;
    
    private Packet prototype = null;
    private short packetSize = -1;
    
    public PacketDecoder(PacketRegistry packetRegistry) {
        this.packetRegistry = packetRegistry;
    }
    
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        isPacketPrototypeReady(in);
        isPacketSizeReady(in);
        
        try {
            buildAndPassPacket(in, out);
        } finally {
            prototype = null;
            packetSize = -1;
        }
    }
    
    private void buildAndPassPacket(ByteBuf in, List<Object> out) throws PacketException, InstantiationException, IllegalAccessException {
        prototype.unpack(in);
        out.add(prototype);
    }
    
    private boolean isPacketPrototypeReady(ByteBuf in) {
        if(prototype == null) {
            if(in.readableBytes() >= 1) {
                prototype = packetRegistry.getNewPacketInstance(in.readByte());
            } else {
                return false;
            }
        }
        return true;
    }
    
    private boolean isPacketSizeReady(ByteBuf in) {
        if(packetSize == -1) {
            if(in.readableBytes() >= 2) {
                packetSize = in.readShort();
            } else {
                return false;
            }
        }
        return true;
    }
}