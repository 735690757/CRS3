package edu.beihua.KarryCode.mapReduce1JobMainTest;

import edu.beihua.KarryCode.MapReduceForCVLike.JobMain;
import edu.beihua.KarryCode.MapReduceForCVLike.S1.JobMain1;
import edu.beihua.KarryCode.MapReduceForCVLike.S2.JobMain2;
import edu.beihua.KarryCode.SpringbootTest;
import edu.beihua.KarryCode.service.Impl.MapReduceLikeServiceImpl;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/10/13 上午 8:48
 * @PackageName edu.beihua.KarryCode.mapReduce1JobMainTest
 * @ClassName test1
 * @Description TODO
 * @Version 1.0
 */
public class test1 extends SpringbootTest {
    @Autowired
    JobMain1 jobMain1;
    @Autowired
    JobMain2 jobMain2;
    @Test
    public void test() throws Exception {
        String[] args = new String[12];
        Configuration configuration = new Configuration();
        int run = ToolRunner.run(configuration, new JobMain1(), args);

    }

    @Test
    public void test2() throws Exception {
        String[] args = new String[12];
        Configuration configuration = new Configuration();
        int run = ToolRunner.run(configuration, new JobMain2(), args);

    }
    @Test
    public void testAll() throws Exception {
        String[] args = new String[12];

        Configuration configuration = new Configuration();
        int run1 = ToolRunner.run(configuration, jobMain1, args);
        int run2 = ToolRunner.run(configuration, jobMain2, args);

    }
    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }
        folder.delete();
    }
    @Test
    public void testX(){
        File folderToDelete1 = new File("D:\\A code\\IDEA_Code\\Car_Rental_System_3.0\\CV_Like_S1_output");
        File folderToDelete2 = new File("D:\\A code\\IDEA_Code\\Car_Rental_System_3.0\\CV_Like_S2_output");
        if (folderToDelete1.exists() && folderToDelete1.isDirectory()) {
            deleteFolder(folderToDelete1);
        }
        if (folderToDelete2.exists() && folderToDelete2.isDirectory()) {
            deleteFolder(folderToDelete2);
        }
    }
    @Autowired
    JobMain jobMain;
    @Test
    public void testA(){
        jobMain.MapReduceStater();
    }
    @Test
    public void testANS(){
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
    }


    @Autowired
    MapReduceLikeServiceImpl mapReduceLikeService;
    @Test
    public void testZZZZZZZZ(){
        mapReduceLikeService.CommonPreferences();
    }
}
