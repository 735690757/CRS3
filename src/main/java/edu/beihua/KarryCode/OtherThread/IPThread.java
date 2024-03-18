package edu.beihua.KarryCode.OtherThread;

import edu.beihua.KarryCode.service.ISystemStartupServiceMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/23 下午 2:14
 * @PackageName edu.beihua.KarryCode.OtherThread
 * @ClassName IPThread
 * @Description TODO
 * @Version 1.0
 */
@Component
public class IPThread extends Thread{
    @Autowired
    ISystemStartupServiceMongo iSystemStartupServiceMongo;
    @Override
    public void run() {
        URL url = null;
        try {
            url = new URL("https://api64.ipify.org?format=text");
        } catch (MalformedURLException e) {

        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String IP = reader.readLine();
//            System.out.println("Public IP Address: " + string);
            iSystemStartupServiceMongo.start(IP);
        } catch (UnknownHostException e) {
            iSystemStartupServiceMongo.start("UNKNOWN");
        }
        catch (ConnectException e){
            iSystemStartupServiceMongo.start("Connection timed out");
        }
        catch (IOException e) {

        }
    }
}
