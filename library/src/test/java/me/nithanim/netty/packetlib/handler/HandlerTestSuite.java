package me.nithanim.netty.packetlib.handler;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    me.nithanim.netty.packetlib.handler.PacketDecoderTest.class,
    me.nithanim.netty.packetlib.handler.PacketEncoderTest.class,
    me.nithanim.netty.packetlib.handler.PacketHandlerTest.class,
})
public class HandlerTestSuite {
}
