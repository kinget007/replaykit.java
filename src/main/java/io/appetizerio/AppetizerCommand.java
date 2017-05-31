package io.appetizerio;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by luoy on 2017/5/31.
 */
public class AppetizerCommand {
    public AppetizerCommand(Path binPath) {
        mbinPath = binPath;
    }

    int executeCommand(List<String> args) throws IOException, InterruptedException {
        String[] argArray = new String[args.size()];
        argArray = args.toArray(argArray);
        return executeCommand(argArray);
    }

    int executeCommand(String... args) throws IOException, InterruptedException {
        String[] newArgs = new String[args.length + 1];
        newArgs[0] = mbinPath.toString();
        System.arraycopy(args, 0, newArgs, 1, args.length);
        Process p = new ProcessBuilder().command(newArgs).inheritIO().start();
        int exitCode = p.waitFor();
        return exitCode;
    }

    void showErrorMessage(String msg) {
        System.err.println(msg);
    }

    private Path mbinPath;
}
