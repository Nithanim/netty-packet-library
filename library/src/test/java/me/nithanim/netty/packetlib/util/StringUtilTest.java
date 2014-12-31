package me.nithanim.netty.packetlib.util;

import io.netty.util.CharsetUtil;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilTest {
    private final String testString = "This is a teststring!";
    
    @Test
    public void testGetUtf8Size() {
        int expected = testString.getBytes(CharsetUtil.UTF_8).length;
        int result = StringUtil.getUtf8Size(testString);
        assertEquals(expected, result);
    }
}
