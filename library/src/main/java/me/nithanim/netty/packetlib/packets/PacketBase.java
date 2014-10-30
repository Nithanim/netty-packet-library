package me.nithanim.netty.packetlib.packets;

import me.nithanim.netty.packetlib.processing.PacketProcessor;

/**
 * This is a basic implementation of {@link Packet} that you can extend for some
 * basic functions.
 * 
 * @param <PACKET_PROCESSOR> Same as {@link Packet}
 * @see Packet
 * @see PacketProcessor
 */
public abstract class PacketBase<PACKET_PROCESSOR extends PacketProcessor> implements Packet<PACKET_PROCESSOR> {
    @Override
    public int getPacketSize() {
        return Byte.SIZE/8 + Short.SIZE/8 + getPayloadSize();
    }
}
