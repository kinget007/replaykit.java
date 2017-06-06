package io.appetizerio;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by luoy on 2017/6/5.
 */
public class TraceCommand extends AppetizerCommand {
    public TraceCommand(Path binPath) {
        super(binPath);
    }

    public String getInfo(String pathToFile) throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
        return executeCommandGetOutput("trace", "info", pathToFile);
    }

    public RunningTaskControl record(String traceFilePath) throws IOException {
        return record("127.0.0.1", 5037, null, traceFilePath);
    }

    public RunningTaskControl record(String host, int port, String traceFilePath) throws IOException {
        return record(host, port, null, traceFilePath);
    }

    public RunningTaskControl record(String deviceSn, String traceFilePath) throws IOException {
        return record("127.0.0.1", 5037, deviceSn, traceFilePath);
    }

    public RunningTaskControl record(String host, int port, String deviceSn, String traceFilePath) throws IOException {
        if (deviceSn == null) {
            return executeCommandGetRunningTask("trace", "record", "--port", String.valueOf(port), "--host",
                    host, traceFilePath);
        } else {
            return executeCommandGetRunningTask("trace", "record", "--port", String.valueOf(port), "--host",
                    host, "--device", deviceSn, traceFilePath);
        }
    }

    public RunningTaskControl replay(String traceFilePath, String deviceSnList) throws IOException {
        return replay("127.0.0.1", 5037, traceFilePath, deviceSnList);
    }


    public RunningTaskControl replay(String host, int port, String traceFilePath, String deviceSnList) throws IOException {
        return executeCommandGetRunningTask("trace", "replay", "--host", host, "--port",
                String.valueOf(port), traceFilePath, deviceSnList);
    }
}
