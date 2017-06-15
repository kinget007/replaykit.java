package io.appetizerio;

/**
 * Created by luoy on 2017/6/15.
 */
public class ADBServer {
    private String mHost;
    private int mPort;

    public ADBServer(String host, int port) {
        mHost = host;
        mPort = port;
    }

    public ADBServer() {
        mHost = "127.0.0.1";
        mPort = 5037;
    }

    public String getHost() {
        return mHost;
    }

    public int getPort() {
        return mPort;
    }
}
