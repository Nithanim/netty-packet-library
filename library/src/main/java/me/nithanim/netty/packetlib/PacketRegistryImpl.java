package me.nithanim.netty.packetlib;

import me.nithanim.netty.packetlib.packets.Packet;
import java.util.HashMap;
import java.util.Map;

public class PacketRegistryImpl implements PacketRegistry {
    private final Map<Integer, Class<? extends Packet>> idToPacketMap = new HashMap<Integer, Class<? extends Packet>>();

    @Override
    public void registerPacket(Class<? extends Packet> packetClass) throws IllegalArgumentException {
        Packet packet = getNewPacketInstanceReflective(packetClass);
        int packetId = packet.getId();
        
        if(packetId > Byte.MAX_VALUE || packetId < Byte.MIN_VALUE) {
            throw new IllegalArgumentException("PacketId needs to be in byte-range for " + packetClass.getName());
        }

        if(isIdRegistered(packetId)) {
            throw new IllegalArgumentException(packetClass.getName() + ": PacketId " + packetId + " is already registered by " + idToPacketMap.get(packetId) + "!");
        }

        idToPacketMap.put(packetId, packetClass);
    }

    @Override
    public Packet getNewPacketInstance(int packetId) {
        if(!isIdRegistered(packetId)) {
            throw new IllegalArgumentException("PacketId is not registered!");
        }

        return getNewPacketInstanceReflective(idToPacketMap.get(packetId));
    }

    private Packet getNewPacketInstanceReflective(Class<? extends Packet> packetClass) {
        try {
            return packetClass.newInstance();
        } catch(InstantiationException e) {
            throw new IllegalArgumentException("Unable to instantiate " + packetClass.getName() + ". Did you forget a default constructor?", e);
        } catch(IllegalAccessException e) {
            throw new IllegalArgumentException("Unable to instantiate " + packetClass.getName() + ".", e);
        }
    }

    @Override
    public boolean isIdRegistered(int id) {
        return idToPacketMap.containsKey(id);
    }
}
