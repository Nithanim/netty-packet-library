package me.nithanim.netty.packetlib.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.Arrays;
import me.nithanim.netty.packetlib.packets.Packet;
import me.nithanim.netty.packetlib.testpackets.TestPacketEmpty;
import me.nithanim.netty.packetlib.testpackets.TestPacketFull;
import me.nithanim.netty.packetlib.testpackets.TestPacketReferenceCounted;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PacketEncoderTest {
    private static PacketEncoder encoder;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        encoder = new PacketEncoder();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testEncodeEmpty() throws Exception {
        Packet packet = new TestPacketEmpty();
        ByteBuf expected = packetAsBuffer(packet);
        ByteBuf encoded = getExactByteBuffer(packet.getPacketSize());
        encoder.encode(null, packet, encoded);
        assertEquals(expected, encoded);
    }
    
    @Test
    public void testEncodeFull() throws Exception {
        Packet packet = new TestPacketFull(456253454, 53634);
        ByteBuf expected = packetAsBuffer(packet);
        ByteBuf encoded = getExactByteBuffer(packet.getPacketSize());
        encoder.encode(null, packet, encoded);
        System.out.println(Arrays.toString(expected.array()));
        System.out.println(Arrays.toString(encoded.array()));
        assertEquals(expected, encoded);
    }
    
    @Test
    public void testEncodeMultiple() throws Exception {
        Packet[] packets = new Packet[] {
                new TestPacketEmpty(),
                new TestPacketFull(32, 34234),
                new TestPacketEmpty(),
                new TestPacketFull(234234235, 214421),
                new TestPacketEmpty()
            };
        int sizeOfPackets = getSizeOfPacketArray(packets);
        
        ByteBuf encoded = Unpooled.buffer(sizeOfPackets);
        for(Packet packet : packets) {
            encoder.encode(null, packet, encoded);
        }
        
        ByteBuf expected = Unpooled.buffer(sizeOfPackets);
        for(Packet packet : packets) {
            expected.writeBytes(packetAsBuffer(packet));
        }
        
        System.out.println(Arrays.toString(expected.array()));
        System.out.println(Arrays.toString(encoded.array()));
        assertEquals(expected, encoded);
    }
    
    @Test
    public void testReferenceCountDecrease() throws Exception {
        TestPacketReferenceCounted packet = new TestPacketReferenceCounted();
        ByteBuf buffer = getExactByteBuffer(packet.getPacketSize());
        
        assertTrue(packet.refCnt() == 1);
        encoder.encode(null, packet, buffer);
        assertTrue(packet.refCnt() == 0);
    }
    
    private int getSizeOfPacketArray(Packet[] packets) {
        int size = 0;
        for(Packet packet : packets) {
            size += packet.getPacketSize();
        }
        return size;
    }
    
    private ByteBuf packetAsBuffer(Packet packet) {
        ByteBuf buffer = getExactByteBuffer(packet.getPacketSize());
        writePacketMetaToByteBuf(packet, buffer);
        packet.pack(buffer);
        return buffer;
    }
    
    private void writePacketMetaToByteBuf(Packet packet, ByteBuf buf) {
        buf.writeByte(packet.getId());
        buf.writeShort(packet.getPayloadSize());
    }
    
    private ByteBuf getExactByteBuffer(int payloadSize) {
        return Unpooled.buffer(payloadSize, payloadSize);
    }
}
