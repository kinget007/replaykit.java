package io.appetizerio;

import com.google.common.base.Joiner;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by luoy on 2017/6/6.
 */
public class PlanCommand extends AppetizerCommand {
    public PlanCommand(Path binPath) {
        super(binPath);
    }

    public RunningTaskControl run(String planFile, String resultFolder, List<String> snList) throws IOException {
        return run("127.0.0.1", 5037, planFile, resultFolder, snList, null);
    }

    public RunningTaskControl run(String planFile, String resultFolder, List<String> snList, String reportUrl) throws IOException {
        return run("127.0.0.1", 5037, planFile, resultFolder, snList, reportUrl);
    }

    public RunningTaskControl run(String host, int port, String planFile, String resultFolder,
                                  List<String> snList, String reportUrl) throws IOException {
        String sn = Joiner.on(",").skipNulls().join(snList);
        if (reportUrl == null) {
            return executeCommandGetRunningTask("plan", "run", "--host", host, "--port", String.valueOf(port),
                    planFile, resultFolder, sn);
        } else {
            return executeCommandGetRunningTask("plan", "run", "--host", host, "--port", String.valueOf(port),
                    planFile, resultFolder, sn, "--report-url", reportUrl);
        }
    }
}
