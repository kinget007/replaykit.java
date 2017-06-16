package io.appetizerio;

import com.google.common.base.Joiner;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by luoy on 2017/6/6.
 */
public class DeviceCommand extends AppetizerCommand {
    public DeviceCommand(Path binPath) {
        super(binPath);
    }

    public String list() throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
        return list(new ADBServer());
    }

    public String list(ADBServer server) throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
        return executeCommandGetOutput("devices", "list", "--host", server.getHost(), "--port", String.valueOf(server.getPort()));
    }

    public RunningTaskControl mirror(String masterSn, List<String> slaveSnList) throws IOException {
        return mirror(new ADBServer(), masterSn, slaveSnList);
    }

    public RunningTaskControl mirror(ADBServer server, String masterSn, List<String> slaveSnList) throws IOException {
        String slaveSn = Joiner.on(",").skipNulls().join(slaveSnList);
        return executeCommandGetRunningTask("devices", "mirror", "--host", server.getHost(), "--port", String.valueOf(server.getPort()),
                masterSn, slaveSn);
    }

    public String control(List<String> snList, String command, List<String> args) throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
        return control(new ADBServer(), snList, command, args);
    }

    public String control(ADBServer server, List<String> snList, String command, List<String> args) throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
        String sn = Joiner.on(",").skipNulls().join(snList);
        String[] newArgs = new String[args.size() + 8];
        newArgs[0] = "devices";
        newArgs[1] = "control";
        newArgs[2] = "--host";
        newArgs[3] = server.getHost();
        newArgs[4] = "--port";
        newArgs[5] = String.valueOf(server.getPort());
        newArgs[6] = sn;
        newArgs[7] = command;
        int index = 8;
        for (String arg : args) {
            newArgs[index] = arg;
            index ++;
        }
        return executeCommandGetOutput(newArgs);
    }

    public void screenshot(String deviceSn, String savePath) throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
        screenshot(new ADBServer(), deviceSn, savePath);
    }

    public void screenshot(ADBServer server, String deviceSn, String savePath) throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
         executeCommand("devices", "screenshot", "--host", server.getHost(), "--port", String.valueOf(server.getPort()),
                "--device", deviceSn, savePath);
    }
}