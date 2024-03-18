package edu.beihua.KarryCode;

import edu.beihua.KarryCode.OtherThread.IPThread;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/23 下午 1:28
 * @PackageName edu.beihua.KarryCode
 * @ClassName IPTest
 * @Description TODO
 * @Version 1.0
 */
public class IPTest extends SpringbootTest{
    public static String getPublicIPAddress() throws IOException {
        // 使用一个公共的IP地址查询服务
        URL url = new URL("https://api64.ipify.org?format=text");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return reader.readLine();
        }
    }
    @Autowired
    IPThread ipThread;
    @Test
    public void test() throws UnknownHostException, MalformedURLException {
        ipThread.start();
//        ipThread.r
        URL url = new URL("https://api64.ipify.org?format=text");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String string = reader.readLine();System.out.println("Public IP Address: " + string);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
