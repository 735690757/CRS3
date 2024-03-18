package edu.beihua.KarryCode.MapReduceForCVLike.S2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/10/13 上午 9:07
 * @PackageName edu.beihua.KarryCode.MapReduceForCVLike.S2
 * @ClassName Step2Reducer
 * @Description TODO 将左侧逐对拆分，并将结果输出
 * @Version 1.0
 */
public class Step2Reducer extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        StringBuffer stringBuffer = new StringBuffer();
        for (Text value : values) {
            stringBuffer.append(value.toString()).append("-");
        }
        context.write(key,new Text(stringBuffer.toString()));
    }
}
