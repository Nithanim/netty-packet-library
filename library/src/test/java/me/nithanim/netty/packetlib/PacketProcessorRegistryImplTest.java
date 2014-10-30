package me.nithanim.netty.packetlib;

import me.nithanim.netty.packetlib.packets.Packet;
import me.nithanim.netty.packetlib.processing.PacketProcessor;
import me.nithanim.netty.packetlib.testpackets.TestPacketEmpty;
import me.nithanim.netty.packetlib.testpackets.TestPacketFull;
import me.nithanim.netty.packetlib.testpackets.TestPacketProcessorEmpty;
import me.nithanim.netty.packetlib.testpackets.TestPacketProcessorFull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PacketProcessorRegistryImplTest {
    private PacketProcessorRegistryImpl packetProcessorRegistry;
    @Before
    public void setUp() {
        packetProcessorRegistry = new PacketProcessorRegistryImpl();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testPacketMatchingPacketEmpty() {
        Packet packet = new TestPacketEmpty();
        packetProcessorRegistry.registerPacketProcessor(new TestPacketProcessorEmpty(null));
        
        PacketProcessor packetProcessor = packetProcessorRegistry.getPacketProcessorFor(packet);
        packet.processPacket(packetProcessor);
    }
    
    @Test
    public void testPacketMatchingPacketFull() {
        Packet packet = new TestPacketFull();
        packetProcessorRegistry.registerPacketProcessor(new TestPacketProcessorFull(null));
        
        PacketProcessor packetProcessor = packetProcessorRegistry.getPacketProcessorFor(packet);
        packet.processPacket(packetProcessor);
    }
    
    @Test
    public void testPacketMatchingMultiplePackets() {
        Packet packetFull = new TestPacketFull();
        packetProcessorRegistry.registerPacketProcessor(new TestPacketProcessorFull(null));
        Packet packetEmpty = new TestPacketEmpty();
        packetProcessorRegistry.registerPacketProcessor(new TestPacketProcessorEmpty(null));
        
        
        PacketProcessor packetProcessorFull = packetProcessorRegistry.getPacketProcessorFor(packetFull);
        PacketProcessor packetProcessorEmpty = packetProcessorRegistry.getPacketProcessorFor(packetEmpty);
        packetEmpty.processPacket(packetProcessorEmpty);
        packetFull.processPacket(packetProcessorFull);
    }
    
}
