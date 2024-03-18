package edu.beihua.KarryCode.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/23 下午 1:26
 * @PackageName edu.beihua.KarryCode.entity
 * @ClassName SystemStartupLog
 * @Description TODO
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Document("SystemStartupLog")
public class SystemStartupLog {
    private String startTime;
    private String hostAddress;
    private String hostName;
}
