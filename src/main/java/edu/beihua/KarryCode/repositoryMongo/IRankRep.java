package edu.beihua.KarryCode.repositoryMongo;

import edu.beihua.KarryCode.entity.RankEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/24 下午 4:52
 * @PackageName edu.beihua.KarryCode.repositoryMongo
 * @ClassName RankWrite
 * @Description TODO
 * @Version 1.0
 */
public interface IRankRep {
    void Write(ArrayList<RankEntity> rankEntities);
    List<RankEntity> Read();
}
