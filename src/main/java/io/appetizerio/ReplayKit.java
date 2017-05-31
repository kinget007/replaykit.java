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
        this("127.0.01", 5037, binBasePath);
    }

    public ReplayKit(String host, int port, String binBasePath) throws FileNotFoundException {
        mADBHost = host;
        mADBPort = port;
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
            mBinPath = Paths.get(binBasePath, targetBinName);
        } else {
            throw new FileNotFoundException();
        }
    }

    private String mADBHost;
    private Path mBinPath;
    private int mADBPort;
}
