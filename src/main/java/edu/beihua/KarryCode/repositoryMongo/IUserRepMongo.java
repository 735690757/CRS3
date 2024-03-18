package edu.beihua.KarryCode.repositoryMongo;

import edu.beihua.KarryCode.entity.User;

import edu.beihua.KarryCode.entity.UserLogonLog;
import edu.beihua.KarryCode.repositoryMongo.impl.UserRepMongoImpl;
import edu.beihua.KarryCode.tools.Permissions;

import java.util.List;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/17 下午 6:26
 * @PackageName edu.beihua.KarryCode.repositoryMongo
 * @ClassName IUserRepMongo
 * @Description TODO
 * @Version 1.0
 */
public interface IUserRepMongo {
    void Log_UserLoginSuccess(User user, Permissions permissions);
    void Log_UserLoginFailure(String visName, Permissions permissions);
    List<UserLogonLog> Log_ShowAllUserRepMongo();
    List<UserLogonLog> Log_ShowPaginationUserRepMongo(int pageNumber, int pageSize);
    long Log_ShowLogCount();

}
