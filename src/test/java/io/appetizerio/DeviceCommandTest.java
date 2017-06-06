package io.appetizerio;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by luoy on 2017/6/6.
 */
public class DeviceCommandTest {
    @Test
    public void screenshot() throws Exception {
        ReplayKit kit = new ReplayKit(Util.BIN_PATH);
        kit.Device().screenshot(Util.TEST_DEVICE_SN, Util.SCREENSHOT_PATH);
    }

    @Test
    public void control() throws Exception {
        ReplayKit kit = new ReplayKit(Util.BIN_PATH);
//        String result = kit.Device().control(Arrays.asList(Util.TEST_DEVICE_SN, Util.TEST_DEVICE_SN_2),
//                "shell", Arrays.asList("pm", "list", "packages"));
        String result = kit.Device().control(Arrays.asList(Util.TEST_DEVICE_SN, Util.TEST_DEVICE_SN_2),
                "shell", Collections.singletonList("ls"));
        System.out.println(result);
    }

    @Test
    public void mirror() throws Exception {
        ReplayKit kit = new ReplayKit(Util.BIN_PATH);
        RunningTaskControl control = kit.Device().mirror(Util.TEST_DEVICE_SN, Collections.singletonList(Util.TEST_DEVICE_SN_2));
        for (int i = 0; i < 10; i ++) {
            System.out.println(String.format("Sleeping %d seconds", i + 1));
            Thread.sleep(1000);
        }
        System.out.println("Kill the record");
        control.stop();
    }

    @Test
    public void list() throws Exception {
        ReplayKit kit = new ReplayKit(Util.BIN_PATH);
        String listResult = kit.Device().list();
        System.out.println(listResult);
    }
}