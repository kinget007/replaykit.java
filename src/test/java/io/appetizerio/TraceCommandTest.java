package io.appetizerio;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

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
            RunningTaskControl control = kit.Trace().record(Util.TEST_DEVICE_SN, Util.TRACE_FILE_NAME);
            for (int i = 0; i < 10; i ++) {
                System.out.println(String.format("Sleeping %d seconds", i + 1));
                Thread.sleep(1000);
            }
            System.out.println("Kill the record");
            control.stop();
            String traceInfo = kit.Trace().getInfo(Util.TRACE_FILE_NAME);
            System.out.println(traceInfo);
        } catch (IOException | InterruptedException e) {
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            fail(writer.toString());
        } catch (ReplayKit.AppetizerFailureException e) {
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            String errMsg = writer.toString().trim();
            if (!errMsg.startsWith("Exception AttributeError")) {
                fail(writer.toString());
            }
        }
    }
}