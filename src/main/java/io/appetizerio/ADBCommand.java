package io.appetizerio;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by luoy on 2017/5/31.
 */
public class ADBCommand extends AppetizerCommand {
    public ADBCommand(Path binPath) {
        super(binPath);
    }

    public String checkServer() throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        return checkServer("127.0.0.1:5037");
    }

    public String checkServer(String ...addrList) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        List<String> args = new ArrayList<>();
        args.add("adb");
        args.add("check-server");
        Collections.addAll(args, addrList);
        return executeCommandGetOutput(args);
    }

    public void startServer() throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        startServer("127.0.0.1", 5037);
    }

    public void startServer(String host, int  port) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        executeCommand("adb", "start-server",
                "--host", host, "--port", String.valueOf(port));
    }

    public void startServer(String pathToADB) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        startServer("127.0.0.1", 5037, pathToADB);
    }

    public void startServer(String host, int port, String pathToADB) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        executeCommand("adb", "start-server",
                "--host", host, "--port", String.valueOf(port),
                "--adb", pathToADB);
    }

    public void killServer() throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        killServer("127.0.0.1", 5037);
    }

    public void killServer(String host, int  port) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
            executeCommand("adb", "kill-server",
                    "--host", host, "--port", String.valueOf(port));
    }

    public void killServer(String pathToADB) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        killServer("127.0.0.1", 5037, pathToADB);
    }

    public void killServer(String host, int port, String pathToADB) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        executeCommand("adb", "kill-server",
                "--host", host, "--port", String.valueOf(port),
                "--adb", pathToADB);
    }

    public String detectADB() throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        return executeCommandGetOutput("adb", "detectadb");
    }
}
