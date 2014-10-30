package me.nithanim.netty.packetlib.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import me.nithanim.netty.packetlib.testpackets.TestPacketFull;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Helperclass to test basic functions. In your @Before you need to call
 * {@link #setPacket(me.nithanim.netty.packetlib.packets.Packet)} for your
 * Packet you want to test.
 * You may also want to add tests specific for packing and unpacking alone
 * since this cannot be done by this helper.
 */
public abstract class GenericPacketTest {
    Packet packet;
    
    @After
    public void tearDown() {
        packet = null;
    }
    
    @Test
    public void testPackAndUnpack() {
        ByteBuf buffer = getExactByteBuffer(packet.getPayloadSize());
        packet.pack(buffer);
        
        ByteBuf copyBuffer = buffer.copy();
        TestPacketFull newPacket = new TestPacketFull();
        
        newPacket.unpack(copyBuffer);
        assertEquals(packet, newPacket);
        
        ByteBuf newBuffer = getExactByteBuffer(newPacket.getPayloadSize());
        newPacket.pack(newBuffer);
        assertEquals(copyBuffer, newBuffer);
    }
    
    public Packet getPacket() {
        return packet;
    }
    
    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    private ByteBuf getExactByteBuffer(int payloadSize) {
        return Unpooled.buffer(payloadSize, payloadSize);
    }
}
