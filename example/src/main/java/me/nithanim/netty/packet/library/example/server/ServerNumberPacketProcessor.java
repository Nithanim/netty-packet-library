package me.nithanim.netty.packet.library.example.server;

import io.netty.channel.Channel;
import me.nithanim.netty.packet.library.example.server.common.CommonNumberPacketProcessor;
import me.nithanim.netty.packet.library.example.server.common.PacketNumber;

public class ServerNumberPacketProcessor extends CommonNumberPacketProcessor {
    private Integer firstNumber;
    
    public ServerNumberPacketProcessor(Channel channel) {
        super(channel);
    }
    
    public void processNumberPacket(PacketNumber packet) {
        System.out.println("Got number from client: " + packet.getNumber());
        
        if(firstNumber == null) {
            firstNumber = packet.getNumber();
        } else {
            int newNumber = packet.getNumber();
            int result = newNumber + firstNumber;
            System.out.println(firstNumber + " + " + newNumber + " = " + result);
            System.out.println("Sending " + result);
            sendPacket(new PacketNumber(result));
            getChannel().close();
        }
    }
}
