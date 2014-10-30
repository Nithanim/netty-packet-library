package me.nithanim.netty.packet.library.example.client;

import io.netty.channel.Channel;
import me.nithanim.netty.packet.library.example.server.common.CommonNumberPacketProcessor;
import me.nithanim.netty.packet.library.example.server.common.PacketNumber;

public class ClientNumberPacketProcessor extends CommonNumberPacketProcessor {
    public ClientNumberPacketProcessor(Channel channel) {
        super(channel);
    }
    
    public void processNumberPacket(PacketNumber packet) {
        System.out.println("Got number from server: " + packet.getNumber());
        getChannel().close();
    }
}