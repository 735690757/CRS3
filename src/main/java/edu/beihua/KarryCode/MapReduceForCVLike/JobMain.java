package edu.beihua.KarryCode.MapReduceForCVLike;

import edu.beihua.KarryCode.MapReduceForCVLike.S0.Step0Pretreatment;
import edu.beihua.KarryCode.MapReduceForCVLike.S1.JobMain1;
import edu.beihua.KarryCode.MapReduceForCVLike.S2.JobMain2;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/10/13 上午 9:22
 * @PackageName edu.beihua.KarryCode.MapReduceForCVLike
 * @ClassName JobMain
 * @Description TODO
 * @Version 1.0
 */
@Component
public class JobMain {
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
    @Autowired
    Step0Pretreatment step0Pretreatment;
    @Autowired
    JobMain1 jobMain1;
    @Autowired
    JobMain2 jobMain2;
    public void MapReduceStater(){
        File folderToDelete1 = new File("D:\\A code\\IDEA_Code\\Car_Rental_System_3.0\\CV_Like_S1_output");
        File folderToDelete2 = new File("D:\\A code\\IDEA_Code\\Car_Rental_System_3.0\\CV_Like_S2_output");
        if (folderToDelete1.exists() && folderToDelete1.isDirectory()) {
            deleteFolder(folderToDelete1);
        }
        if (folderToDelete2.exists() && folderToDelete2.isDirectory()) {
            deleteFolder(folderToDelete2);
        }
        try {
            step0Pretreatment.getCustomerVehicle();
            String[] args = new String[12];
            Configuration configuration = new Configuration();
            int run1 = ToolRunner.run(configuration, jobMain1, args);
            int run2 = ToolRunner.run(configuration, jobMain2, args);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
