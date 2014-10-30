package me.nithanim.netty.packetlib;

import me.nithanim.netty.packetlib.packets.Packet;
import me.nithanim.netty.packetlib.processing.PacketProcessor;

/**
 * Here, {@link PacketProcessor}s can be registerd to be available for
 * processing decoded {@link Packet}s. 
 * Every {@link io.netty.channel.Channel} needs its own Registry because most
 * packets depend/opereate on their {@link io.netty.channel.Channel}.
 * 
 * @see io.netty.channel.Channel
 * @see Packet
 * @see PacketProcessor
 */
public interface PacketProcessorRegistry {
    public void registerPacketProcessor(PacketProcessor packetProcessor);
    
    /**
     * Searches for a matching {@link PacketProcessor} that can handle a 
     * specific {@link Packet}
     * 
     * @param packet a {@link Packet} to get the {@link PacketProcessor} for
     * @return the apropriate {@link PacketProcessor}
     */
    public PacketProcessor getPacketProcessorFor(Packet packet);
}
