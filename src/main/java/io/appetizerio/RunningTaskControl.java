package io.appetizerio;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;

/**
 * Created by luoy on 2017/6/6.
 */
public class RunningTaskControl {
    private Process mProcess;
    private String mCommandName;
    public RunningTaskControl(String commandName, Process p) {
        mCommandName = commandName;
        mProcess = p;
    }

    /**
     * Signal the task to stop gracefully.
     */
    void stop() throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        OutputStream out = mProcess.getOutputStream();
        out.write("please quit\n".getBytes());
        if (mProcess.isAlive()) {
            out.flush();
        }
        int exitCode = mProcess.waitFor();
        if (exitCode != 0) {
            StringWriter write = new StringWriter();
            IOUtils.copy(mProcess.getErrorStream(), write, Charset.defaultCharset());
            String errMsg = write.toString();
            throw new ReplayKit.AppetizerFailureException(mCommandName, errMsg);
        }
    }

    void waitToFinish() throws InterruptedException {
        int exitCode = mProcess.waitFor();
        if (exitCode != 0) {
            StringWriter write = new StringWriter();
            try {
                IOUtils.copy(mProcess.getErrorStream(), write, Charset.defaultCharset());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String errMsg = write.toString();
            try {
                throw new ReplayKit.AppetizerFailureException(mCommandName, errMsg);
            } catch (ReplayKit.AppetizerFailureException e) {
                e.printStackTrace();
            }
        }
    }
}
