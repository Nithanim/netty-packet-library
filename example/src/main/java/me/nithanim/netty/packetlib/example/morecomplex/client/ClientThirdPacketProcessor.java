package me.nithanim.netty.packetlib.example.morecomplex.client;

import io.netty.channel.Channel;
import me.nithanim.netty.packetlib.example.morecomplex.common.CommonThirdPacketProcessor;
import me.nithanim.netty.packetlib.example.morecomplex.common.PacketThird;
import me.nithanim.netty.packetlib.processing.PacketProcessorBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientThirdPacketProcessor extends PacketProcessorBase implements CommonThirdPacketProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ClientThirdPacketProcessor.class);
    
    public ClientThirdPacketProcessor(Channel channel) {
        super(channel);
    }

    @Override
    public void processThirdPacket(PacketThird packet) {
        logger.info("Processed {}", packet.getClass().getSimpleName());
    }
    
}