package me.nithanim.netty.packetlib.example.morecomplex.common;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import me.nithanim.netty.packetlib.packets.Packet;
import org.slf4j.Logger;

public abstract class CommonDebugHandler extends ChannelHandlerAdapter {
    protected Logger logger;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof Packet) {
            Packet packet = (Packet) msg;
            if(packet instanceof PacketFirst) {
                PacketFirst p = (PacketFirst) packet;
                logger.info("Got a {} with \"{}\" and \"{}\"", p.getClass().getSimpleName(), p.getSomeRandomInteger(), p.getSomeRandomFloat());
            } else if(packet instanceof PacketSecond) {
                PacketSecond p = (PacketSecond) packet;
                logger.info("Got a {} with \"{}\"", p.getClass().getSimpleName(), p.getString());
            } else if(packet instanceof PacketThird) {
                logger.info("Got a {}", packet.getClass().getSimpleName());
            }
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if(msg instanceof Packet) {
            Packet packet = (Packet) msg;
            if(packet instanceof PacketFirst) {
                PacketFirst p = (PacketFirst) packet;
                logger.info("Sent a {} with \"{}\" and \"{}\"", p.getClass().getSimpleName(), p.getSomeRandomInteger(), p.getSomeRandomFloat());
            } else if(packet instanceof PacketSecond) {
                PacketSecond p = (PacketSecond) packet;
                logger.info("Sent a {} with \"{}\"", p.getClass().getSimpleName(), p.getString());
            } else if(packet instanceof PacketThird) {
                logger.info("Sent a {}", packet.getClass().getSimpleName());
            }
        }
        super.write(ctx, msg, promise);
    }
}
