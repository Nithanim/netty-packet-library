package me.nithanim.netty.packetlib.processing;

import io.netty.channel.Channel;
import me.nithanim.netty.packetlib.packets.Packet;

/**
 * See the interface {@link PacketProcessor} for information.
 * 
 * @see PacketProcessor
 */
public class PacketProcessorBase implements PacketProcessor {
    private final Channel channel;

    public PacketProcessorBase(Channel channel) {
        this.channel = channel;
    }
    
    @Override
    public void sendPacket(Packet packet) {
        channel.writeAndFlush(packet);
    }

    public Channel getChannel() {
        return channel;
    }
}
