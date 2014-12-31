package me.nithanim.netty.packetlib.example.morecomplex.client;

import io.netty.channel.Channel;
import me.nithanim.netty.packetlib.example.morecomplex.common.CommonFirstPacketProcessor;
import me.nithanim.netty.packetlib.example.morecomplex.common.PacketFirst;
import me.nithanim.netty.packetlib.example.morecomplex.server.ServerSecondAndThirdPacketProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  As {@link ServerSecondAndThirdPacketProcessor}, this Processor is useless in this example,
 *  because no {@link PacketFirst} is sent from the server.
 */
public class ClientFirstPacketProcessor extends CommonFirstPacketProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ClientFirstPacketProcessor.class);
    
    public ClientFirstPacketProcessor(Channel channel) {
        super(channel);
    }

    @Override
    public void processFirstPacket(PacketFirst packet) {
        throw new UnsupportedOperationException("Not supported.");
    }
}