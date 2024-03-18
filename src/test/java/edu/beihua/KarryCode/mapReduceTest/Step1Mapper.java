package edu.beihua.KarryCode.mapReduceTest;

import edu.beihua.KarryCode.entity.CustomerVehicle;
import edu.beihua.KarryCode.mapper.IOrderMapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/10/12 下午 9:28
 * @PackageName edu.beihua.KarryCode.mapReduceTest
 * @ClassName Step1Mapper
 * @Description TODO
 * @Version 1.0
 */

public class Step1Mapper extends Mapper<LongWritable, Text,Text,Text> {

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("=");
        String k2 = split[0];
        String v2 = split[1];
        System.out.println(k2+"="+v2);
        context.write(new Text(k2),new Text(v2));
    }
}
