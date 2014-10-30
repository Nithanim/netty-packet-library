package me.nithanim.netty.packetlib;

import me.nithanim.netty.packetlib.packets.Packet;

/**
 * At this class, packets can be registered to be able to decode them. It can
 * (should) be used across multiple {@link io.netty.channel.Channel}s that use
 * the same Packets to save on memory.
 * 
 * @see me.nithanim.netty.packetlib.processing.PacketProcessor
 */
public interface PacketRegistry {
    public void registerPacket(Class<? extends Packet> packetClass);
    public Packet getNewPacketInstance(int packetId);
    public boolean isIdRegistered(int id);
}
