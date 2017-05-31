package io.appetizerio;


import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

/**
 * Created by luoy on 2017/5/31.
 */
public class ReplayKitTest {
    @Test
    public void testConstructor() {
        // invalid path
        try {
            ReplayKit kit = new ReplayKit("/code/");
            fail();
        } catch (FileNotFoundException e) {
        }

        // valid path for my machine
        try {
            ReplayKit kit = new ReplayKit("D:\\code\\appetizer\\appetizer-toolkit\\win32");
            kit.showDebugMessage();
        } catch (FileNotFoundException e) {
            fail();
        }
    }
}