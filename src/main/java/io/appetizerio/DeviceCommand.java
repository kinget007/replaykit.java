package io.appetizerio;

import com.google.common.base.Joiner;
import jdk.nashorn.internal.scripts.JO;

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
        return list("127.0.0.1", 5037);
    }

    public String list(String host, int port) throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
        return executeCommandGetOutput("devices", "list", "--host", host, "--port", String.valueOf(port));
    }

    public RunningTaskControl mirror(String masterSn, List<String> slaveSnList) throws IOException {
        return mirror("127.0.0.1", 5037, masterSn, slaveSnList);
    }

    public RunningTaskControl mirror(String host, int port, String masterSn, List<String> slaveSnList) throws IOException {
        String slaveSn = Joiner.on(",").skipNulls().join(slaveSnList);
        return executeCommandGetRunningTask("devices", "mirror", "--host", host, "--port", String.valueOf(port),
                masterSn, slaveSn);
    }

    public String control(List<String> snList, String command, List<String> args) throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
        return control("127.0.0.1", 5037, snList, command, args);
    }

    public String control(String host, int port, List<String> snList, String command, List<String> args) throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
        String sn = Joiner.on(",").skipNulls().join(snList);
        String[] newArgs = new String[args.size() + 8];
        newArgs[0] = "devices";
        newArgs[1] = "control";
        newArgs[2] = "--host";
        newArgs[3] = host;
        newArgs[4] = "--port";
        newArgs[5] = String.valueOf(port);
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
        screenshot("127.0.0.1", 5037, deviceSn, savePath);
    }

    public void screenshot(String host, int port, String deviceSn, String savePath) throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
         executeCommand("devices", "screenshot", "--host", host, "--port", String.valueOf(port),
                "--device", deviceSn, savePath);
    }
}