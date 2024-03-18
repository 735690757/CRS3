package edu.beihua.KarryCode.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/11 下午 8:31
 * @PackageName edu.karrycode.mongodb01.entity
 * @ClassName t_stu
 * @Description TODO
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("t_stu")
public class t_stu {
    @Id
    private String id;
    private ArrayList<String> hobbies;

    @Override
    public String toString() {
        return "t_stu{" +
                "id=" + id +
                ", hobbies=" + hobbies +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    private String name;
    private String sex;

}
