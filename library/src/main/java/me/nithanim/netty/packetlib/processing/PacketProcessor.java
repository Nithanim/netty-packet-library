package me.nithanim.netty.packetlib.processing;

import me.nithanim.netty.packetlib.packets.Packet;

/**
 * A interface which is implemented by a class to state that it can process
 * a packet i.e. can do something with the contents.
 * <br />
 * Usually, a PacketProcessor should implement a method in the form of
 * <code>public void processPacketTest(PacketTest packet)</code>
 * where "PacketTest" is replaced by the name of the Packet you want to be
 * able to process. This method is then called by this packets 
 * {@link Packet#processPacket(PacketProcessor)}.
 * <br />
 * It is possible for one PacketProcessor to implement multiple
 * <code>processPacket*()</code> methods.
 * 
 * @see Packet
 */
public interface PacketProcessor {
    public void sendPacket(Packet packet);
}
