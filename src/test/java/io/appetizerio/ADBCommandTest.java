package io.appetizerio;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by luoy on 2017/5/31.
 */
public class ADBCommandTest {
    @Test
    public void checkServer() {
        try {
            ReplayKit kit = new ReplayKit("D:\\code\\appetizer\\appetizer-toolkit\\win32");
            int retcode = kit.ADB().checkServer();
            retcode = kit.ADB().checkServer("127.0.0.1:8080");
            retcode = kit.ADB().checkServer("127.0.0.1:8080", "127.0.0.1:8081");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}