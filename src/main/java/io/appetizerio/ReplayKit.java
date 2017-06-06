package io.appetizerio;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by luoy on 2017/5/31.
 */
public class ReplayKit {

    public ReplayKit(String binBasePath) throws FileNotFoundException {
        String targetBinName = null;
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac") || osName.contains("darwin")) {
            targetBinName = "appetizer";
        } else if (osName.contains("win")) {
            targetBinName = "appetizer.exe";
        } else if (osName.contains("nux")) {
            targetBinName = "appetizer";
        } else {
            throw new RuntimeException("Unsupported platform");
        }
        File targetFile = new File(binBasePath, targetBinName);
        if (targetFile.exists() && !targetFile.isDirectory()) {
            mBinPath = Paths.get(binBasePath, targetBinName).toAbsolutePath();
        } else {
            throw new FileNotFoundException();
        }

        mADBCommad = new ADBCommand(mBinPath);
        mTrceCommand = new TraceCommand(mBinPath);
        mDeviceCommnad = new DeviceCommand(mBinPath);
        mPlanCommand = new PlanCommand(mBinPath);
        mInsightCommand = new InsightCommand(mBinPath);
    }

    public void showDebugMessage() {
        System.out.println(String.format("binary path: %s", mBinPath.toString()));
    }
    public ADBCommand ADB() {
        return mADBCommad;
    }
    public TraceCommand Trace() { return mTrceCommand; }
    public DeviceCommand Device() { return mDeviceCommnad; }
    public PlanCommand Plan() { return mPlanCommand; }
    public InsightCommand Insight() { return mInsightCommand; }

    private Path mBinPath;
    private ADBCommand mADBCommad;
    private TraceCommand mTrceCommand;
    private DeviceCommand mDeviceCommnad;
    private PlanCommand mPlanCommand;
    private InsightCommand mInsightCommand;

    public static class AppetizerFailureException extends Exception {
        private String mCmd;
        public AppetizerFailureException(String cmd, String errMsg) {
            super(errMsg);
            mCmd = cmd;
        }
    }
}
