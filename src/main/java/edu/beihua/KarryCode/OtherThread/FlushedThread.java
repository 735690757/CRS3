package edu.beihua.KarryCode.OtherThread;

import edu.beihua.KarryCode.entity.RankEntity;
import edu.beihua.KarryCode.repositoryMongo.impl.RankRepImpl;
import edu.beihua.KarryCode.repositoryRedis.generic.RedisRepGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/24 下午 5:24
 * @PackageName edu.beihua.KarryCode.OtherThread
 * @ClassName flushedThread
 * @Description TODO
 * @Version 1.0
 */
@Component
public class FlushedThread extends Thread {
    @Autowired
    RedisRepGeneric redisRepGeneric;
    @Autowired
    RankRepImpl rankRep;
    private volatile boolean stopped = false;
    @Override
    public void run() {
        while (!stopped) {
            Map<String, Double> stringDoubleMap = redisRepGeneric.ViewLeaderboard();
            ArrayList<RankEntity> rankEntities = new ArrayList<>();
            for (String VName : stringDoubleMap.keySet()) {
                Double score = stringDoubleMap.get(VName);
                rankEntities.add(new RankEntity(VName, score));
            }
            rankRep.Write(rankEntities);

            List<RankEntity> rankEntitiesX = rankRep.Read();
            redisRepGeneric.Update(rankEntitiesX);
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
            }
        }

        Map<String, Double> stringDoubleMap = redisRepGeneric.ViewLeaderboard();
        ArrayList<RankEntity> rankEntities = new ArrayList<>();
        for (String VName : stringDoubleMap.keySet()) {
            Double score = stringDoubleMap.get(VName);
            rankEntities.add(new RankEntity(VName, score));
        }
        rankRep.Write(rankEntities);

    }
    public void stopThread() {
        stopped = true;
    }
}
