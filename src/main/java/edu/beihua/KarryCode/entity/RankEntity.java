package edu.beihua.KarryCode.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/24 下午 4:01
 * @PackageName edu.beihua.KarryCode.entity
 * @ClassName RankEntity
 * @Description TODO
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document("RankEntity")
public class RankEntity {
    private String name;
    private Double score;
}
