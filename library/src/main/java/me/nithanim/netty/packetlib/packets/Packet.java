package me.nithanim.netty.packetlib.packets;

import io.netty.buffer.ByteBuf;
import me.nithanim.netty.packetlib.processing.PacketProcessor;

/**
 * A packet is a holder for data which can be transmitted over the network.
 * It should be registered at a {@link  me.nithanim.netty.packetlib.PacketProcessorRegistry}
 * so that it can be decoded.
 * 
 * @param <PACKET_PROCESSOR> a implementation of {@link PacketProcessor} which
 * can process a packet
 * @see PacketProcessor
 * @see me.nithanim.netty.packetlib.PacketRegistry
 */
public interface Packet<PACKET_PROCESSOR extends PacketProcessor> {
    /**
     * The packetId is used to identify and decode a packet. It can only be
     * one byte long!
     * 
     * @return packetId
     */
    public int getId();
    
    /**
     * The byte-size of the whole packet including the id (byte), the size
     * of the payload (short) + {@link #getPayloadSize()}.
     * 
     * @return the whole size of packet in bytes
     * @see #getPayloadSize() 
     */
    public int getPacketSize();
    
    /**
     * Returns the size of the content of the packet in bytes.
     * 
     * @return the size of the contents in bytes.
     */
    public int getPayloadSize();
    
    /**
     * Encodes the contents of the packet into the {@link ByteBuf} given by
     * parameter.
     * 
     * @param buffer where the contents of the packet is packed into
     * @see #unpack(ByteBuf)
     * @see ByteBuf
     */
    public void pack(ByteBuf buffer);
    
    /**
     * Decodes the contents of the {@link ByteBuf} back into a packet. It is the
     * revese operation of {@link #pack(ByteBuf)}. You need to make sure that
     * the contents are decoded in the same order as they are encoded!
     * 
     * @param buffer where the contents of the packets are stored
     * @see #pack(ByteBuf)
     * @see ByteBuf
     */
    public void unpack(ByteBuf buffer);
    
    /**
     * When a packet is decoded this method is called. It needs to call
     * its apropriate processing method that is defined in the {@link PacketProcessor}.
     * 
     * @param packetProcessor the {@link PacketProcessor} with an apropriate
     * mehtod for this packet
     * 
     * @see PacketProcessor
     */
    public void processPacket(PACKET_PROCESSOR packetProcessor);
}
