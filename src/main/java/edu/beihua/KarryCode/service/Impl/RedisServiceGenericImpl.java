package edu.beihua.KarryCode.service.Impl;

import edu.beihua.KarryCode.entity.RankEntity;
import edu.beihua.KarryCode.repositoryRedis.generic.RedisRepGeneric;
import edu.beihua.KarryCode.service.IRedisServiceGeneric;
import edu.beihua.KarryCode.tools.AccountUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/24 下午 3:39
 * @PackageName edu.beihua.KarryCode.service.Impl
 * @ClassName RedisServiceGenericImpl
 * @Description TODO
 * @Version 1.0
 */
@Service
public class RedisServiceGenericImpl implements IRedisServiceGeneric {
    @Autowired
    RedisRepGeneric redisRepGeneric;
    @Override
    public void QueryLeaderboard() {
        while (true) {
            Map<String, Double> stringDoubleMap = redisRepGeneric.ViewLeaderboard();
//        Map<String, Double> sortedMap = stringDoubleMap.entrySet()
//                .stream()
//                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue,
//                        (e1, e2) -> e1,
//                        LinkedHashMap::new
//                ));
            ArrayList<RankEntity> rankEntities = new ArrayList<>();

            for (String VName : stringDoubleMap.keySet()) {
                Double score = stringDoubleMap.get(VName);
    //            System.out.println("Key: " + VName + ", Value: " + score);
                rankEntities.add(new RankEntity(VName, score));
            }
            Comparator<RankEntity> comparator = Comparator.comparingDouble(RankEntity::getScore).reversed();
            Collections.sort(rankEntities, comparator);
            System.out.println("  车名                                     热度值" );

            for (RankEntity entity : rankEntities) {
                String name = entity.getName();
                int length = name.length();
                if (45-length*2>=0){
                    for (int i = 0; i < 45-length*2; i++) {
                        name+=" ";
                    }
                }
                System.out.println(name + entity.getScore());
            }

            System.out.println("\n\n1. 刷新            2.退出");
            int select = AccountUtility.readInt();
            switch (select){
                case 1:{
                    break;
                }
                case 2:{
                    return;
                }
                default:{
                    System.out.println("选项不正确");
                }
            }
        }

    }
}
