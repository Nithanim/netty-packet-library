package me.nithanim.netty.packetlib.example.morecomplex.client;

import io.netty.channel.Channel;
import me.nithanim.netty.packetlib.example.morecomplex.common.CommonSecondPacketProcessor;
import me.nithanim.netty.packetlib.example.morecomplex.common.PacketSecond;
import me.nithanim.netty.packetlib.processing.PacketProcessorBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientSecondPacketProcessor extends PacketProcessorBase implements CommonSecondPacketProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ClientSecondPacketProcessor.class);
    public ClientSecondPacketProcessor(Channel channel) {
        super(channel);
    }
    
    @Override
    public void processSecondPacket(PacketSecond packet) {
        logger.info("Processed {} and got {}", packet.getClass().getSimpleName(), packet.getString());
    }
}
