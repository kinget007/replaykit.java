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
        return record(new ADBServer(), null, traceFilePath);
    }

    public RunningTaskControl record(ADBServer server, String traceFilePath) throws IOException {
        return record(server, null, traceFilePath);
    }

    public RunningTaskControl record(String deviceSn, String traceFilePath) throws IOException {
        return record(new ADBServer(), deviceSn, traceFilePath);
    }

    public RunningTaskControl record(ADBServer server, String deviceSn, String traceFilePath) throws IOException {
        if (deviceSn == null) {
            return executeCommandGetRunningTask("trace", "record", "--port", String.valueOf(server.getPort()), "--host",
                    server.getHost(), traceFilePath);
        } else {
            return executeCommandGetRunningTask("trace", "record", "--port", String.valueOf(server.getPort()), "--host",
                    server.getHost(), "--device", deviceSn, traceFilePath);
        }
    }

    public RunningTaskControl replay(String traceFilePath, String deviceSnList) throws IOException {
        return replay(new ADBServer(), traceFilePath, deviceSnList);
    }


    public RunningTaskControl replay(ADBServer server, String traceFilePath, String deviceSnList) throws IOException {
        return executeCommandGetRunningTask("trace", "replay", "--host", server.getHost(), "--port",
                String.valueOf(server.getPort()), traceFilePath, deviceSnList);
    }
}
