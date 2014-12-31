package me.nithanim.netty.packetlib;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    me.nithanim.netty.packetlib.PacketProcessorRegistryImplTest.class,
    me.nithanim.netty.packetlib.PacketRegistryImplTest.class,
    me.nithanim.netty.packetlib.handler.HandlerTestSuite.class,
    me.nithanim.netty.packetlib.util.UtilTestSuite.class,
})
public class MainTestSuite {
}
