package me.nithanim.netty.packetlib.example.simpleadder.client;

import io.netty.channel.Channel;
import me.nithanim.netty.packetlib.example.simpleadder.common.CommonNumberPacketProcessor;
import me.nithanim.netty.packetlib.example.simpleadder.common.PacketNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientNumberPacketProcessor extends CommonNumberPacketProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ClientNumberPacketProcessor.class);
    
    public ClientNumberPacketProcessor(Channel channel) {
        super(channel);
    }
    
    @Override
    public void processNumberPacket(PacketNumber packet) {
        logger.info("Received number: {}", packet.getNumber());
        getChannel().close();
    }
}