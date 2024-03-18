package edu.beihua.KarryCode.entity;

import lombok.*;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/10/12 下午 9:38
 * @PackageName edu.beihua.KarryCode.entity
 * @ClassName CustomerVehicle
 * @Description TODO
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class CustomerVehicle {
    private String customer_name;
    private String Vehicle_name;
}
