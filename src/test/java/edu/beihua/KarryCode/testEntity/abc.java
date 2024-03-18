package edu.beihua.KarryCode.testEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/10/12 下午 8:27
 * @PackageName edu.beihua.KarryCode
 * @ClassName entity
 * @Description TODO
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Scope("prototype")
public class abc {
    private String id;
    private String f1;
    private String f2;
}
