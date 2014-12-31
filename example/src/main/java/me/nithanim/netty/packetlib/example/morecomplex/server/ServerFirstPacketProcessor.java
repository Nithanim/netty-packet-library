package me.nithanim.netty.packetlib.example.morecomplex.server;

import io.netty.channel.Channel;
import me.nithanim.netty.packetlib.example.morecomplex.common.CommonFirstPacketProcessor;
import me.nithanim.netty.packetlib.example.morecomplex.common.PacketFirst;
import me.nithanim.netty.packetlib.example.morecomplex.common.PacketSecond;
import me.nithanim.netty.packetlib.example.morecomplex.common.PacketThird;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerFirstPacketProcessor extends CommonFirstPacketProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ServerFirstPacketProcessor.class);
    
    public ServerFirstPacketProcessor(Channel channel) {
        super(channel);
    }
    
    @Override
    public void processFirstPacket(PacketFirst packet) {
        PacketSecond response1 = new PacketSecond("Hi there!");
        logger.info("Processed {} and got {} and {}", packet.getClass().getSimpleName(), packet.getSomeRandomInteger(), packet.getSomeRandomFloat());
        PacketThird response2 = new PacketThird();
        getChannel().write(response1);
        getChannel().write(response2);
        getChannel().flush();
    }
}
