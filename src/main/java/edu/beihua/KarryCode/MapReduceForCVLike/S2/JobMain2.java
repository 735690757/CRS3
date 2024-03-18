package edu.beihua.KarryCode.MapReduceForCVLike.S2;

import edu.beihua.KarryCode.MapReduceForCVLike.S1.Step1Mapper;
import edu.beihua.KarryCode.MapReduceForCVLike.S1.Step1Reducer;
import edu.beihua.KarryCode.MapReduceForCVLike.S2.Step2Mapper;
import edu.beihua.KarryCode.MapReduceForCVLike.S2.Step2Reducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.springframework.stereotype.Component;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/10/13 上午 9:11
 * @PackageName edu.beihua.KarryCode.MapReduceForCVLike
 * @ClassName JobMain2
 * @Description TODO 二轮MapReduce
 * @Version 1.0
 */
@Component
public class JobMain2 extends Configured implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
//        Job job = Job.getInstance(super.getConf(), "CV_Like");
        Job job = Job.getInstance(new Configuration(),"CV_Like2");
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job,new Path("file:///D:\\A code\\IDEA_Code\\Car_Rental_System_3.0\\CV_Like_S1_output\\part-r-00000"));

        job.setMapperClass(Step2Mapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setReducerClass(Step2Reducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job,new Path("file:///D:\\A code\\IDEA_Code\\Car_Rental_System_3.0\\CV_Like_S2_output"));

        boolean b = job.waitForCompletion(true);
        return b?0:1;
    }
}
