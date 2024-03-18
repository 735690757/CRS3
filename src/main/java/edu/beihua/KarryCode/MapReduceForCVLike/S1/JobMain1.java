package edu.beihua.KarryCode.MapReduceForCVLike.S1;

import edu.beihua.KarryCode.MapReduceForCVLike.S1.Step1Mapper;
import edu.beihua.KarryCode.MapReduceForCVLike.S1.Step1Reducer;
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
 * @Date 2023/10/12 下午 10:37
 * @PackageName edu.beihua.KarryCode.mapReduceTest
 * @ClassName JobMain
 * @Description TODO 一轮MapReduce
 * @Version 1.0
 */
@Component
public class JobMain1 extends Configured implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
//        Job job = Job.getInstance(super.getConf(), "CV_Like");
        Job job = Job.getInstance(new Configuration(),"CV_Like");
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job,new Path("file:///D:\\A code\\IDEA_Code\\Car_Rental_System_3.0\\CV_Like_S1_input\\SourceData.txt"));

        job.setMapperClass(Step1Mapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setReducerClass(Step1Reducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job,new Path("file:///D:\\A code\\IDEA_Code\\Car_Rental_System_3.0\\CV_Like_S1_output"));

        boolean b = job.waitForCompletion(true);
        return b?0:1;
    }


//    public static void main(String[] args) throws Exception {
//        Configuration configuration = new Configuration();
//        int run = ToolRunner.run(configuration, new JobMain(), args);
//        System.exit(run);
//    }
}
