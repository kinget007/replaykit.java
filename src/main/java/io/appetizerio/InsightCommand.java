package io.appetizerio;

import com.google.common.base.Joiner;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by luoy on 2017/6/6.
 */
public class InsightCommand extends AppetizerCommand {
    public InsightCommand(Path binPath) {
        super(binPath);
    }

    void fetchInsightLog(List<String> snList, String savePath, String pkg, boolean clear) throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
        fetchInsightLog("127.0.0.1", 5037, snList, savePath, pkg, clear);
    }

    void fetchInsightLog(String host, int port, List<String> snList, String savePath, String pkg, boolean clear) throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
        String sn = Joiner.on(",").skipNulls().join(snList);
        if (clear) {
            executeCommand("insight", "fetchlog", "--host", host, "--port", String.valueOf(port),
                    pkg, sn, savePath, "--clear");
        } else {
            executeCommand("insight", "fetchlog", "--host", host, "--port", String.valueOf(port),
                    pkg, sn, savePath);

        }
    }

    void clearInsightLog(List<String> snList, String pkg) throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
        clearInsightLog("127.0.0.1", 5037, snList, pkg);
    }

    void clearInsightLog(String host, int port, List<String> snList, String pkg) throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
        String sn = Joiner.on(",").skipNulls().join(snList);
        executeCommand("insight", "clear", "--host", host, "--port", String.valueOf(port), pkg, sn);
    }
}
