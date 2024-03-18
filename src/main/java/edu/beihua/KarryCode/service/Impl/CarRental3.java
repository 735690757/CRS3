package edu.beihua.KarryCode.service.Impl;

import edu.beihua.KarryCode.Command_Control.CommandView;
import edu.beihua.KarryCode.repositoryMongo.impl.MessageLogImplRepMongo;
import edu.beihua.KarryCode.repositoryRedis.generic.RedisRepGeneric;
import edu.beihua.KarryCode.service.IMessageLogeService;
import edu.beihua.KarryCode.service.ISystemStartupServiceMongo;
import edu.beihua.KarryCode.service.IUserServiceMongo;
import edu.beihua.KarryCode.tools.AccountUtility;
import edu.beihua.KarryCode.view.PanelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

/**
 * @Author KarryLiu_刘珂瑞
 * @Creed may all the beauty be blessed
 * @Date 2023/9/23 下午 9:51
 * @PackageName edu.beihua.KarryCode.service.Impl
 * @ClassName RankServiceImpl
 * @Description TODO
 * @Version 1.0
 */
@Service
public class CarRental3 {
    @Autowired
    ISystemStartupServiceMongo iSystemStartupServiceMongo;
    @Autowired
    MapReduceLikeServiceImpl mapReduceLikeService;
    @Autowired
    RedisServiceGenericImpl redisServiceGeneric;
    @Autowired
    IMessageLogeService iMessageLogeService;
    @Autowired
    IUserServiceMongo iUserServiceMongo;

    public<T> boolean examine(T s){
        String ss=(String) s;
        if (new CommandView().Command(ss)==1){
            System.out.println("控制级主动中断");
            AccountUtility.readReturn();
            return true;
        }
        return false;
    }
    public void CoreView3() {
        Scanner scanner = new Scanner(System.in);
        PanelView panelView = new PanelView();

        while (true){
//            panelView.panelForAdmin();
            panelView.CRS3();

            String select = scanner.next();
            if (select.matches("-?\\d+(\\.\\d+)?")) {
                System.out.println("|等待系统响应");
            } else {
                examine(select);
                continue;
            }
            int select2;
            try {
                select2=Integer.parseInt(select);
            }catch (NumberFormatException e) {
                select2 = 0;
                System.out.println("|不带你这么玩的奥！");
            }
            switch (select2){
                case 1:{
                    redisServiceGeneric.QueryLeaderboard();
                    break;
                }
                case 2:{
                    iUserServiceMongo.PaginationUserRepMongo();
//                    System.out.println("——————————————————用户查询————————————————");
//                    System.out.println("1.以用户名进行查询（模糊查询）            2.查询所有用户");
//                    System.out.println("|请输入：");
//                    int select3 = AccountUtility.readInt();
//                    switch (select3) {
//                        case 1: {
//
//                            break;
//                        }
//                        case 2:{
//
//                            break;
//                        }
//                        default:{
//                            System.out.println("|选项无效！");
//                            break;
//                        }
//                    }
                    break;
                }
                case 3:{
                    iSystemStartupServiceMongo.showLogRecentTen();
                    break;
                }
                case 4:{
                    iMessageLogeService.showMessage();
                    break;
                }
                case 5:{
                    mapReduceLikeService.CommonPreferences();
                    break;
                }
                case 6:{
                    System.out.println("——————————————————退出————————————————");
                    AccountUtility.readReturn();
                    return;
                }

                default:{
                    System.out.println("|选项不存在！");
                    AccountUtility.readReturn();
                    break;
                }
            }
        }
    }
}
