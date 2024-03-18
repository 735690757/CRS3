package edu.beihua.KarryCode.MapReduceForCVLike.S2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/10/13 上午 8:53
 * @PackageName edu.beihua.KarryCode.MapReduceForCVLike.S2
 * @ClassName Step2Mapper
 * @Description TODO Map，将一轮的数据，进行回喂
 * @Version 1.0
 */
public class Step2Mapper extends Mapper<LongWritable, Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        //拆分得V2
        String[] split = value.toString().split("\t");
        String v2 = split[1];
        //拆分得K2+排序
        String[] user = split[0].split("-");
        Arrays.sort(user);
        //组合得真正的K2         TODO 现在凌晨12点 困死了
        for (int i = 0; i < user.length-1; i++) {
            for (int j = i+1; j < user.length; j++) {
                context.write(new Text(user[i]+"-"+user[j]),new Text(v2));            }
        }

    }
}
