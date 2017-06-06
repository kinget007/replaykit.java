package io.appetizerio;

import org.junit.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by luoy on 2017/6/6.
 */
public class PlanCommandTest {
    @Test
    public void run() throws Exception {
        ReplayKit kit = new ReplayKit(Util.BIN_PATH);
        RunningTaskControl control = kit.Plan().run(Util.TEST_PALN,
                Paths.get("test_data", "result").toAbsolutePath().toString(),
                Arrays.asList(Util.TEST_DEVICE_SN, Util.TEST_DEVICE_SN_2));
        control.waitToFinish();
    }
}