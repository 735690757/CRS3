package edu.beihua.KarryCode.entity;

import lombok.*;
import edu.beihua.KarryCode.tools.Permissions;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.text.SimpleDateFormat;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/17 下午 4:09
 * @PackageName edu.beihua.KarryCode.entity
 * @ClassName UserLogonLog
 * @Description TODO 用户登录日志
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Document("UserLogonLog")
public class UserLogonLog {
    private String logonUserName;
    private String date;
    private Permissions Permissions;
    @Field(targetType = FieldType.BOOLEAN)
    private Boolean Visible;
    private boolean Successful;
}
