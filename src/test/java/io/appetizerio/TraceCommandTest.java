package io.appetizerio;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by luoy on 2017/6/6.
 */
public class TraceCommandTest {
    @Test
    public void replay() throws Exception {
        ReplayKit kit = new ReplayKit(Util.BIN_PATH);
        String traceInfo = kit.Trace().getInfo(Util.TRACE_FILE_NAME);
        System.out.println(traceInfo);
        RunningTaskControl control = kit.Trace().replay(Util.TRACE_FILE_NAME, Util.TEST_DEVICE_SN);
        control.waitToFinish();
    }

    @Test
    public void record(){
        ReplayKit kit = null;
        try {
            kit = new ReplayKit(Util.BIN_PATH);
            RunningTaskControl control = kit.Trace().record(Util.TRACE_FILE_NAME);
            for (int i = 0; i < 10; i ++) {
                System.out.println(String.format("Sleeping %d seconds", i + 1));
                Thread.sleep(1000);
            }
            System.out.println("Kill the record");
            control.stop();
            String traceInfo = kit.Trace().getInfo(Util.TRACE_FILE_NAME);
            System.out.println(traceInfo);
        } catch (FileNotFoundException e) {
            fail();
            e.printStackTrace();
        } catch (IOException e) {
            fail();
            e.printStackTrace();
        } catch (InterruptedException e) {
            fail();
            e.printStackTrace();
        } catch (ReplayKit.AppetizerFailureException e) {
            fail();
            e.printStackTrace();
        }
    }
}