package edu.beihua.KarryCode.MapReduceForCVLike.S1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/10/12 下午 10:06
 * @PackageName edu.beihua.KarryCode.mapReduceTest
 * @ClassName Step1Reducer
 * @Description TODO 将结果用”-“连接，键值反转
 * @Version 1.0
 */
@Component
public class Step1Reducer extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        //遍历集合，拼接得到K3
        StringBuffer stringBuffer = new StringBuffer();
        for (Text value : values) {
            stringBuffer.append(value.toString()).append("-");
        }
        //K2就是V3
        //写入上下文
        context.write(new Text(stringBuffer.toString()),key);
    }
}
