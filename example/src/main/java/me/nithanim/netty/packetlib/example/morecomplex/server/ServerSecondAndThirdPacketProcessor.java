package me.nithanim.netty.packetlib.example.morecomplex.server;

import io.netty.channel.Channel;
import me.nithanim.netty.packetlib.example.morecomplex.common.CommonSecondAndThirdPacketProcessor;
import me.nithanim.netty.packetlib.example.morecomplex.common.PacketSecond;
import me.nithanim.netty.packetlib.example.morecomplex.common.PacketThird;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This PacketProcessor is only for completness and is unnecessary for this example
 * since no PacketSecond and PAcketThird are ever sent to the server.
 * It might be a good idea to implement them though so it throws an exception if
 * a client sends a packet that is not expected by the server.
 */
public class ServerSecondAndThirdPacketProcessor extends CommonSecondAndThirdPacketProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ServerSecondAndThirdPacketProcessor.class);
    
    public ServerSecondAndThirdPacketProcessor(Channel channel) {
        super(channel);
    }
    
    @Override
    public void processSecondPacket(PacketSecond packet) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void processThirdPacket(PacketThird packet) {
        throw new UnsupportedOperationException("Not supported");
    }
}
