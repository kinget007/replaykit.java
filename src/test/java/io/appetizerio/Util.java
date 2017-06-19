package io.appetizerio;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by luoy on 2017/6/6.
 */
public class Util {
    public static String BIN_PATH;
    public static final String TRACE_FILE_NAME = "trace.zip";
    public static final String TEST_DEVICE_SN = "9c91fa10";
    public static final String TEST_DEVICE_SN_2 = "28ea91a07d53";
    public static final String SCREENSHOT_PATH = "test.png";
    public static final String TEST_PALN = Paths.get("test_data", "test_plan.txt").toAbsolutePath().toString();
    static {
        String appetizerHome = System.getenv("APPETIZER_TOOLKIT").trim();
        String osName = System.getProperty("os.name").toLowerCase();
        String dirName = null;
        if (osName.contains("mac") || osName.contains("darwin")) {
            dirName = "darwin";
        } else if (osName.contains("win")) {
            dirName = "win32";
        } else if (osName.contains("nux")) {
            dirName = "linux";
        }
        Path path = Paths.get(appetizerHome, dirName);
        BIN_PATH = path.toString();
    }
}
