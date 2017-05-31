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

    public int checkServer() {
        return checkServer("127.0.0.1:5037");
    }

    public int checkServer(String ...addrList) {
        try {
            List<String> args = new ArrayList<>();
            args.add("adb");
            args.add("check-server");
            Collections.addAll(args, addrList);
            return executeCommand(args);
        } catch (InterruptedException e) {
            showErrorMessage(e.getMessage());
            return 1;
        } catch (IOException e) {
            showErrorMessage(e.getMessage());
            return 1;
        }
    }
}
