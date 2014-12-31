package me.nithanim.netty.packetlib.example.morecomplex.client;

import me.nithanim.netty.packetlib.example.morecomplex.common.CommonDebugHandler;
import org.slf4j.LoggerFactory;

class ClientDebugHandler extends CommonDebugHandler {
    public ClientDebugHandler() {
        logger = LoggerFactory.getLogger(ClientDebugHandler.class);
    }
}
