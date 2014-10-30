package me.nithanim.netty.packetlib.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.nithanim.netty.packetlib.PacketRegistryImpl;
import me.nithanim.netty.packetlib.testpackets.TestPacketEmpty;
import me.nithanim.netty.packetlib.testpackets.TestPacketFull;
import me.nithanim.netty.packetlib.packets.Packet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PacketDecoderTest {
    private static PacketRegistryImpl packetRegistry;
    private static PacketDecoder decoder;
    
    @Before
    public void setUp() {
        packetRegistry = new PacketRegistryImpl();
        decoder = new PacketDecoder(packetRegistry);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testDecodeEmptyPacket() throws Exception {
        packetRegistry.registerPacket(TestPacketEmpty.class);
        TestPacketEmpty packet = TestPacketEmpty.class.newInstance();
        
        ByteBuf from = Unpooled.copiedBuffer(new byte[] {(byte)packet.getId(), 0, 0});
        packet.unpack(from);
    }
    
    @Test
    public void testDecodeFullPacket() throws Exception {
        packetRegistry.registerPacket(TestPacketFull.class);
        TestPacketFull original = new TestPacketFull(10, 11);
        
        ByteBuf input = getExactByteBuffer(original.getPacketSize());
        writePacketMetaToByteBuf(original, input);
        original.pack(input);
        
        byte[] base = new byte[] {99, 0, 12, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 11};
        List<Object> packets = new ArrayList(1);
        decoder.decode(null, input, packets);
        
        assertEquals(1, packets.size());
        Packet decoded = (Packet) packets.get(0);
        
        ByteBuf testBuffer = getExactByteBuffer(decoded.getPacketSize());
        writePacketMetaToByteBuf(decoded, testBuffer);
        decoded.pack(testBuffer);

        assertArrayEquals(base, testBuffer.array());
    }
    
    @Test
    public void testDecodeSeveralPackets() throws Exception {
        ByteBuf fullBuffer = Unpooled.buffer();
        
        packetRegistry.registerPacket(TestPacketEmpty.class);
        packetRegistry.registerPacket(TestPacketFull.class);
        Packet[] packets = new Packet[] {
                new TestPacketEmpty(),
                new TestPacketFull(231, 45345),
                new TestPacketEmpty(),
                new TestPacketFull(5464, 3),
            };
        
        for(Packet packet : packets) {
            ByteBuf packetBuffer = packetAsBuffer(packet);
            fullBuffer.writeBytes(packetBuffer);
        }
        
        ArrayList<Object> decodedPackets = new ArrayList<Object>(packets.length);
        for(int i = 0; i < packets.length; i++) {
            decoder.decode(null, fullBuffer, decodedPackets);
        }
        
        assertEquals(packets.length, decodedPackets.size());
        for(int i = 0; i < packets.length; i++) {
            assertEquals(packetAsBuffer(packets[i]), packetAsBuffer((Packet)decodedPackets.get(i)));
        }
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
    
    private ByteBuf getExactByteBuffer(int size) {
        return Unpooled.buffer(size, size);
    }
    
    private void printArrays(byte[] arr1, byte[] arr2) {
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }
}
