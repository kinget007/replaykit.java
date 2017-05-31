package io.appetizerio;

/**
 * Created by luoy on 2017/5/31.
 */
public class ReplayKit {
    public ReplayKit() {
        mADBHost = "127.0.01";
        mADBPort = 5037;
    }
    public ReplayKit(String host, int port) {
        mADBHost = host;
        mADBPort = port;
    }

    public void showDebugMessage() {
        System.out.println(String.format("Host: %s, Port: %d", mADBHost, mADBPort));
    }
    private String mADBHost;
    private int mADBPort;
}
