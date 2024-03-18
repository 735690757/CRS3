package edu.beihua.KarryCode.entity;

import edu.beihua.KarryCode.tools.MessageActivity;
import edu.beihua.KarryCode.tools.Permissions;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/23 上午 9:58
 * @PackageName edu.beihua.KarryCode.entity
 * @ClassName MessageLog
 * @Description TODO 消息操作日志
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Document("MessageLog")
public class MessageLog {
    private String Operator;
    private MessageActivity messageActivity;
    private String OperationDate;
    private Permissions Permissions;
    private Boolean Visible;
    private boolean Successful;
}
