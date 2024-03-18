package edu.beihua.KarryCode.MapReduceForCVLike.S0;


import edu.beihua.KarryCode.entity.CustomerVehicle;
import edu.beihua.KarryCode.mapper.IOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/10/12 下午 9:35
 * @PackageName edu.beihua.KarryCode.mapReduceTest
 * @ClassName Step0Pretreatment
 * @Description TODO 从数据库中拿数据
 * @Version 1.0
 */
@Component
public class Step0Pretreatment {
    @Autowired
    IOrderMapper iOrderMapper;
//    String filePath = "./CV_Like_S1_input/SourceData.txt";

    public void getCustomerVehicle() throws IOException {
        FileWriter writer = new FileWriter("./CV_Like_S1_input/SourceData.txt");
        List<CustomerVehicle> customerVehicles = iOrderMapper.selectCustomerVehicle();
        for (CustomerVehicle customerVehicle : customerVehicles) {
            writer.write(customerVehicle.getVehicle_name()+"="+customerVehicle.getCustomer_name()+"\n");
        }
//        writer.append("dwdwdwdc\ndwdwdwdwqdqwdqdqwdqwdqwd");
        writer.close();
    }
}
