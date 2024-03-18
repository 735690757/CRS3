package edu.beihua.KarryCode.service.Impl;

import edu.beihua.KarryCode.MapReduceForCVLike.JobMain;
import edu.beihua.KarryCode.service.IMapReduceLike;
import edu.beihua.KarryCode.tools.AccountUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/10/13 上午 9:35
 * @PackageName edu.beihua.KarryCode.service.Impl
 * @ClassName MapReduceLikeImpl
 * @Description TODO
 * @Version 1.0
 */
@Service
public class MapReduceLikeServiceImpl implements IMapReduceLike {
    @Autowired
    JobMain jobMain;
    @Override
    public void CommonPreferences() {
        jobMain.MapReduceStater();
        String fileName = "D:\\A code\\IDEA_Code\\Car_Rental_System_3.0\\CV_Like_S2_output\\part-r-00000";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\t");
                String twoUser = split[0];
                String car = split[1];
                String[] split1 = twoUser.split("-");
                String user1 = split1[0];
                String user2 = split1[1];
                System.out.println(user1+" 和 "+user2+" 共同喜欢了 " + car + "这辆车，好好好真有缘");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        AccountUtility.readReturn();
    }
}
