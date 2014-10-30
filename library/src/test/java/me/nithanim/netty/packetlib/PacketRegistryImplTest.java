package me.nithanim.netty.packetlib;

import me.nithanim.netty.packetlib.testpackets.TestPacketFull;
import me.nithanim.netty.packetlib.testpackets.TestPacketWithoutDefaultConstructor;
import me.nithanim.netty.packetlib.testpackets.TestPacketIdOutOfByteSpace;
import me.nithanim.netty.packetlib.testpackets.TestPacketEmpty;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PacketRegistryImplTest {
    private PacketRegistryImpl packetRegistry;
    
    public PacketRegistryImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        packetRegistry = new PacketRegistryImpl();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testRegisterPacket() {
        packetRegistry.registerPacket(TestPacketFull.class);
        TestPacketFull packet = new TestPacketFull();
        assertTrue(packetRegistry.isIdRegistered(packet.getId()));
    }

    @Test
    public void testGetNewPacketInstance() {
        TestPacketFull packet = new TestPacketFull();
        packetRegistry.registerPacket(packet.getClass());
        packetRegistry.getNewPacketInstance(packet.getId());
        assertEquals(packet.getClass(), TestPacketFull.class);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testPacketNotRegistered() {
        packetRegistry.getNewPacketInstance(-1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testPacketIdNotInByteRange() {
        packetRegistry.registerPacket(TestPacketIdOutOfByteSpace.class);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testPacketIdDoubleAssign() {
        packetRegistry.registerPacket(TestPacketEmpty.class);
        packetRegistry.registerPacket(TestPacketEmpty.class);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testPacketWithoutDefaultConstructor() {
        packetRegistry.registerPacket(TestPacketWithoutDefaultConstructor.class);
    }
}
