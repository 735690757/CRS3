package edu.beihua.KarryCode.view;

public class PanelView {
    public static void panel(){
        System.out.println("\n" +
                "\033[34m" +
               "\n" +
                "   ______              ____             __        __   _____            __               \n" +
                "  / ____/___ ______   / __ \\___  ____  / /_____ _/ /  / ___/__  _______/ /____  ____ ___ \n" +
                " / /   / __ `/ ___/  / /_/ / _ \\/ __ \\/ __/ __ `/ /   \\__ \\/ / / / ___/ __/ _ \\/ __ `__ \\\n" +
                "/ /___/ /_/ / /     / _, _/  __/ / / / /_/ /_/ / /   ___/ / /_/ (__  ) /_/  __/ / / / / /\n" +
                "\\____/\\__,_/_/     /_/ |_|\\___/_/ /_/\\__/\\__,_/_/   /____/\\__, /____/\\__/\\___/_/ /_/ /_/ \n" +
                "                                                         /____/                          \033[0m                                                                                                                                        \n");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {

        }

        System.out.println("+ — — — — 四只\u001B[35m( •̀∀•́ )\u001B[0m草履虫的！！汽!车!租!赁!系统！！— — — — — + — +\u001B[35m٩(๑´0`๑)۶\u001B[0m");
        System.out.println("|               1.用户注册                                |");
        System.out.println("|               2.用户登录                            < 广告招租 >");
        System.out.println("|               3.我有多少钱？                             |");
        System.out.println("|               4.退出登录                      + — — — — + — — — — — +");
        System.out.println("|               5.我要充钱/取钱！                      \033[92m组长：刘珂瑞  \033[0m");
        System.out.println("|               6.让我看看都有什么车                               ");
        System.out.println("|               7.我要租车                       \033[92m ”这是最<大>的一只草履虫“    \033[0m");
        System.out.println("|               8.我租什么车了？？              \033[92m      ↓ \033[0m");
        System.out.println("|               9.还车还车！！                \033[92m组员：洪天宝，范飞龙，李启睿 ← “日常睡觉的草履虫“\033[0m");
        System.out.println("|               10.管理员登录                 \033[92m             ↑\033[0m");
        System.out.println("|               11.管理员退出登录             \033[92m    ”在草履虫模仿大赛中获得<第二名>“\033[0m");
        System.out.println("|               12.进入管理员视图         \033[34m//                 _______                \\\\  \033[0m");
        System.out.println("|               13.查看留言             \033[34m//            ____/ /______\\.___            \\\\  \033[0m");
        System.out.println("|               14.我有话说             \033[34m\\\\         .'    / /_______\\    '.          //  \033[0m");
        System.out.println("|               15.忘记密码              \033[34m\\\\        |====[_]_______[_]====|         //   \033[0m");
        System.out.println("|               16.忘记用户名             \033[34m  ———————————————————————————————————————\033[0m");


        System.out.println("|               18.退出系统       ");
        System.out.println("+ — — — — — — — — — — — — — — — — — — — — — — — — — — — +");
        System.out.println("请选择：                                   \u001B[35m｡:.ﾟヽ(｡◕‿◕｡)ﾉﾟ.:｡+ﾟ\u001B[0m");
    }
    public void panelForAdmin(){
        System.out.println("\n" +
                "\033[34m" +
                "      __           ________       ___      ___       __         _____  ___   \n" +
                "     /\"\"\\         |\"      \"\\     |\"  \\    /\"  |     |\" \\       (\\\"   \\|\"  \\  \n" +
                "    /    \\        (.  ___  :)     \\   \\  //   |     ||  |      |.\\\\   \\    | \n" +
                "   /' /\\  \\       |: \\   ) ||     /\\\\  \\/.    |     |:  |      |: \\.   \\\\  | \n" +
                "  //  __'  \\      (| (___\\ ||    |: \\.        |     |.  |      |.  \\    \\. | \n" +
                " /   /  \\\\  \\     |:       :)    |.  \\    /:  |     /\\  |\\     |    \\    \\ | \n" +
                "(___/    \\___)    (________/     |___|\\__/|___|    (__\\_|_)     \\___|\\____\\) \n" +
                "\033[0m" +
                "                                                                             \n");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {

        }
        System.out.println("+ — — — — 四只\u001B[35m( •̀∀•́ )\u001B[0m草履虫的！！汽!车!租!赁!系统.....\u001B[35m之管理员版\u001B[0m— — + — +\u001B[35m٩(๑´0`๑)۶\u001B[0m");
        System.out.println("|               1.用户查询                  ");
        System.out.println("|               2.车辆信息查询               ");
        System.out.println("|               3.指定车牌号对信息进行修改     ");
        System.out.println("|               4.指定用户名对信息进行修改     ");
        System.out.println("|               5.添加车辆                  ");
        System.out.println("|               6.评论控制                  ");
        System.out.println("|               7.指定车牌号，查看车辆租赁占用信息");

        System.out.println("|               8.退出管理员视图             ");
        System.out.println("+ — — — — — — — — — — — — — — — — — — — — — — — — — — — — +");

        System.out.println("请选择：                                   \u001B[35m｡:.ﾟヽ(｡◕‿◕｡)ﾉﾟ.:｡+ﾟ\u001B[0m");
    }
}
