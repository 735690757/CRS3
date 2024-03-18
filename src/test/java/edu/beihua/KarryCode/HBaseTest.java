//package edu.beihua.KarryCode;
//
//import edu.beihua.KarryCode.testEntity.abc;
//import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hbase.client.*;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.IOException;
//
///**
// * @Author KarryLiu_刘珂瑞
// * @Creed may all the beauty be blessed
// * @Date 2023/10/12 下午 8:26
// * @PackageName edu.beihua.KarryCode
// * @ClassName HBaseTest
// * @Description TODO
// * @Version 1.0
// */
//public class HBaseTest extends SpringbootTest{
//    @Autowired
//    private Connection hbaseConnection;
//    @Test
//    public void test(){
//        try {
//            Table tabc = hbaseConnection.getTable(TableName.valueOf("abc"));
//
//            Get get = new Get("lkr".getBytes());
//            Result result = tabc.get(get);
//            System.out.println(result);
//            byte[] value = result.getValue("f1".getBytes(), null); // 传递 null 作为列限定符
//            if (value != null) {
//                String f2Value = new String(value);
//                System.out.println("f2 value: " + f2Value);
//            } else {
//                System.out.println("No value found in 'f2' column family for row 'lkr'.");
//            }
////            System.out.println("build.getF1() = " + build.getF1());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
