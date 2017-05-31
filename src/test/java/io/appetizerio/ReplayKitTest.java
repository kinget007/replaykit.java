package io.appetizerio;


import org.junit.Test;

/**
 * Created by luoy on 2017/5/31.
 */
public class ReplayKitTest {
    @Test
    public void testShowDebugMessage() {
        ReplayKit kit = new ReplayKit("127.0.0.1", 4444);
        kit.showDebugMessage();
    }
}
