package me.nithanim.netty.packetlib.example.morecomplex.server;

import me.nithanim.netty.packetlib.example.morecomplex.common.CommonDebugHandler;
import org.slf4j.LoggerFactory;

public class ServerDebugHandler extends CommonDebugHandler {
    public ServerDebugHandler() {
        logger = LoggerFactory.getLogger(ServerDebugHandler.class);
    }
}