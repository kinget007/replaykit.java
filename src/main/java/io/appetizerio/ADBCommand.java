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
        ADBServer server = new ADBServer();
        return checkServer(String.format("%s:%d", server.getHost(), server.getPort()));
    }

    public String checkServer(String ...addrList) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        List<String> args = new ArrayList<>();
        args.add("adb");
        args.add("check-server");
        Collections.addAll(args, addrList);
        return executeCommandGetOutput(args);
    }

    public void startServer() throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        startServer(new ADBServer());
    }

    public void startServer(ADBServer server) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        executeCommand("adb", "start-server",
                "--host", server.getHost(), "--port", String.valueOf(server.getPort()));
    }

    public void startServer(String pathToADB) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        startServer(new ADBServer(), pathToADB);
    }

    public void startServer(ADBServer server, String pathToADB) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        executeCommand("adb", "start-server",
                "--host", server.getHost(), "--port", String.valueOf(server.getPort()),
                "--adb", pathToADB);
    }

    public void killServer() throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        killServer(new ADBServer());
    }

    public void killServer(ADBServer server) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
            executeCommand("adb", "kill-server",
                    "--host", server.getHost(), "--port", String.valueOf(server.getPort()));
    }

    public void killServer(String pathToADB) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        killServer(new ADBServer(), pathToADB);
    }

    public void killServer(ADBServer server, String pathToADB) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        executeCommand("adb", "kill-server",
                "--host", server.getHost(), "--port", String.valueOf(server.getPort()),
                "--adb", pathToADB);
    }

    public String detectADB() throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        return executeCommandGetOutput("adb", "detectadb");
    }
}
