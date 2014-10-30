package me.nithanim.netty.packetlib.handler;

import me.nithanim.netty.packetlib.PacketProcessorRegistry;
import me.nithanim.netty.packetlib.PacketProcessorRegistryImpl;
import me.nithanim.netty.packetlib.processing.PacketProcessorBase;
import me.nithanim.netty.packetlib.testpackets.TestPacketReferenceCounted;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PacketHandlerTest {
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testChannelReadReferenceCountDecrease() {
        PacketProcessorRegistry packetProcessorRegistry = new PacketProcessorRegistryImpl();
        packetProcessorRegistry.registerPacketProcessor(new PacketProcessorBase(null));
        PacketHandler handler = new PacketHandler(packetProcessorRegistry);
        TestPacketReferenceCounted packet = new TestPacketReferenceCounted();
        assertTrue(packet.refCnt() == 1);
        handler.channelRead(null, packet);
        assertTrue(packet.refCnt() == 0);
    }
}
