package me.nithanim.netty.packetlib.testpackets;

import io.netty.channel.Channel;
import me.nithanim.netty.packetlib.processing.PacketProcessorBase;

public class TestPacketProcessorEmpty extends PacketProcessorBase {
    public TestPacketProcessorEmpty(Channel channel) {
        super(channel);
    }
    
    public void processPacketTestPacketEmpty(TestPacketEmpty packet) {
        System.out.println("Handling " + packet.getClass().getName());
    }
}
