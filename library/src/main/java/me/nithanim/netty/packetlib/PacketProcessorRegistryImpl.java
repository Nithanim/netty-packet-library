package me.nithanim.netty.packetlib;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.nithanim.netty.packetlib.packets.Packet;
import me.nithanim.netty.packetlib.processing.PacketProcessor;
import net.jodah.typetools.TypeResolver;

public class PacketProcessorRegistryImpl implements PacketProcessorRegistry {
    private final List<PacketProcessor> packetProcessors = new ArrayList<PacketProcessor>();
    private final Map<Class<? extends Packet>, PacketProcessor> packetToMatcherMap = new HashMap<Class<? extends Packet>, PacketProcessor>();
    
    @Override
    public void registerPacketProcessor(PacketProcessor packetProcessor) {
        if(packetProcessors.contains(packetProcessor)) {
            throw new IllegalArgumentException("PacketProcessor already registered!");
        }
        packetProcessors.add(packetProcessor);
    }

    @Override
    public PacketProcessor getPacketProcessorFor(Packet packet) {
        PacketProcessor packetProcessor = packetToMatcherMap.get(packet.getClass());
        if(packetProcessor == null) {
            packetProcessor = findPacketProcessorForPacket(packet);
            packetToMatcherMap.put(packet.getClass(), packetProcessor);
        }
        return packetProcessor;
    }
    
    private PacketProcessor findPacketProcessorForPacket(Packet packet) {
        Type type = TypeResolver.resolveRawArgument(Packet.class, packet.getClass());
        
        for(PacketProcessor packetProcessor : packetProcessors) {
            if(((Class)type).isAssignableFrom(packetProcessor.getClass())) {
                return packetProcessor;
            }
        }
        throw new IllegalArgumentException("Unable to find PacketProcessor for " + packet.getClass().getName());
    }
}
