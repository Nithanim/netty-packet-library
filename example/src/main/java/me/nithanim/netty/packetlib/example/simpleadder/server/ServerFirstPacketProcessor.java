package me.nithanim.netty.packetlib.example.simpleadder.server;

import io.netty.channel.Channel;
import me.nithanim.netty.packetlib.example.simpleadder.common.CommonNumberPacketProcessor;
import me.nithanim.netty.packetlib.example.simpleadder.common.PacketNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerFirstPacketProcessor extends CommonNumberPacketProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ServerFirstPacketProcessor.class);
    
    private Integer firstNumber;
    
    public ServerFirstPacketProcessor(Channel channel) {
        super(channel);
    }
    
    @Override
    public void processNumberPacket(PacketNumber packet) {
        logger.info("Received number: {}", packet.getNumber());
        
        if(firstNumber == null) {
            firstNumber = packet.getNumber();
        } else {
            int secondNumber = packet.getNumber();
            int result = secondNumber + firstNumber;
            logger.info("{} + {} = {}", firstNumber, secondNumber, result);
            
            logger.info("Sending: {}", result);
            sendPacket(new PacketNumber(result));
            getChannel().close();
        }
    }
}
