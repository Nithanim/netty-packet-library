package me.nithanim.netty.packetlib.testpackets;

import io.netty.channel.Channel;
import me.nithanim.netty.packetlib.processing.PacketProcessorBase;

public class TestPacketProcessorFull extends PacketProcessorBase {
    public TestPacketProcessorFull(Channel channel) {
        super(channel);
    }
    
    public void processPacketTestPacket(TestPacketFull packet) {
        System.out.println("Handling " + packet.getClass().getName());
    }
}
