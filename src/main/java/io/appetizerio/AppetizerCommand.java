package io.appetizerio;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;
import org.apache.commons.io.IOUtils;

/**
 * Created by luoy on 2017/5/31.
 */
public class AppetizerCommand {
    public AppetizerCommand(Path binPath) {
        mbinPath = binPath;
    }

    void executeCommand(List<String> args) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        String[] argArray = new String[args.size()];
        argArray = args.toArray(argArray);
        executeCommand(argArray);
    }

    void executeCommand(String... args) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        String[] newArgs = new String[args.length + 1];
        newArgs[0] = mbinPath.toString();
        System.arraycopy(args, 0, newArgs, 1, args.length);
        Process p = new ProcessBuilder().command(newArgs).start();
        int exitCode = p.waitFor();
        if (exitCode != 0) {
            StringWriter write = new StringWriter();
            IOUtils.copy(p.getErrorStream(), write, Charset.defaultCharset());
            String errMsg = write.toString();
            throw new ReplayKit.AppetizerFailureException(args[0], errMsg);
        }
    }

    String executeCommandGetOutput(List<String> args) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        String[] argArray = new String[args.size()];
        argArray = args.toArray(argArray);
        return executeCommandGetOutput(argArray);
    }

    String executeCommandGetOutput(String... args) throws IOException, InterruptedException, ReplayKit.AppetizerFailureException {
        String[] newArgs = new String[args.length + 1];
        newArgs[0] = mbinPath.toString();
        System.arraycopy(args, 0, newArgs, 1, args.length);
        Process p = new ProcessBuilder().command(newArgs).start();
        int exitCode = p.waitFor();
        if (exitCode != 0) {
            StringWriter write = new StringWriter();
            IOUtils.copy(p.getErrorStream(), write, Charset.defaultCharset());
            String errMsg = write.toString();
            throw new ReplayKit.AppetizerFailureException(args[0], errMsg);
        }
        StringWriter write = new StringWriter();
        IOUtils.copy(p.getInputStream(), write, Charset.defaultCharset());
        return write.toString();
    }

    void showErrorMessage(String msg) {
        System.err.println(msg);
    }

    private Path mbinPath;
}
