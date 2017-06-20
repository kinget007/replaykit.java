package io.appetizerio;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by luoy on 2017/5/31.
 */
public class ADBCommandTest {
    @Test
    public void killServer() {
        try {
            ReplayKit kit = new ReplayKit(Util.BIN_PATH);
            System.out.println("check-server");
            String result = kit.ADB().checkServer();
            System.out.println(result);
            System.out.println("start-server");
            kit.ADB().startServer();
            System.out.println("check-server");
            result = kit.ADB().checkServer();
            System.out.println(result);
            System.out.println("kill-server");
            kit.ADB().killServer();
            System.out.println("check-server");
            result = kit.ADB().checkServer();
            System.out.println(result);
        } catch (InterruptedException | ReplayKit.AppetizerFailureException | IOException e) {
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            fail(writer.toString());
        }
    }

    @Test
    public void startServer() throws IOException {
        try {
            ReplayKit kit = new ReplayKit(Util.BIN_PATH);
            kit.ADB().startServer();
            String result = kit.ADB().checkServer();
            System.out.println(result);
        } catch (FileNotFoundException | InterruptedException | ReplayKit.AppetizerFailureException e) {
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            fail(writer.toString());
        }

    }

    @Test
    public void checkServer() {
        try {
            ReplayKit kit = new ReplayKit(Util.BIN_PATH);
            String output = kit.ADB().checkServer();
            System.out.println(output);
            output = kit.ADB().checkServer("127.0.0.1:8080");
            System.out.println(output);
            output = kit.ADB().checkServer("127.0.0.1:8080", "127.0.0.1:8081");
            System.out.println(output);
        } catch (InterruptedException | ReplayKit.AppetizerFailureException | IOException e) {
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            fail(writer.toString());
        }
    }
}