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
        fetchInsightLog(new ADBServer(), snList, savePath, pkg, clear);
    }

    void fetchInsightLog(ADBServer server, List<String> snList, String savePath, String pkg, boolean clear) throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
        String sn = Joiner.on(",").skipNulls().join(snList);
        if (clear) {
            executeCommand("insight", "fetchlog", "--host", server.getHost(), "--port", String.valueOf(server.getPort()),
                    pkg, sn, savePath, "--clear");
        } else {
            executeCommand("insight", "fetchlog", "--host", server.getHost(), "--port", String.valueOf(server.getPort()),
                    pkg, sn, savePath);

        }
    }

    void clearInsightLog(List<String> snList, String pkg) throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
        clearInsightLog(new ADBServer(), snList, pkg);
    }

    void clearInsightLog(ADBServer server, List<String> snList, String pkg) throws InterruptedException, ReplayKit.AppetizerFailureException, IOException {
        String sn = Joiner.on(",").skipNulls().join(snList);
        executeCommand("insight", "clear", "--host", server.getHost(), "--port", String.valueOf(server.getPort()), pkg, sn);
    }
}
