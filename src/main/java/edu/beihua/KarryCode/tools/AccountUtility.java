package edu.beihua.KarryCode.tools;

import java.util.Scanner;

/**
 * 工具类
 */
public class AccountUtility {
    private static Scanner scanner = new Scanner(System.in);

    //界面选项（主界面）
    public static char readMenuSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false);
            c = str.charAt(0);
            if (c != '1' && c != '2' && c != '3' && c != '4') {
                System.out.print("选择错误，请重新输入：");
            } else break;
        }
        return c;
    }


    public static char readMenuSelectionInfor() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false);
            c = str.charAt(0);
            if (c != '1' && c != '2' && c != '3' && c != '4' && c != '5') {
                System.out.print("选择错误，请重新输入：");
            } else break;
        }
        return c;
    }


    /**
     回车
     */
    public static void readReturn() {
        System.out.print("\n按回车键继续...");
        readKeyBoard(100, true);
    }

    /**
     从键盘读取一个长度不超过10位的整数，并将其作为方法的返回值。
     */
    public static int readInt() {
        int n;
        for (; ; ) {
            String str = readKeyBoard(10, false);

            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("数字输入错误，请重新输入：");
            }

        }
        return n;
    }

    /**
     从键盘读取一个长度不超过10位的整数，如果回车不做修改并将其作为方法的返回值。
     */
    public static int readIntU(int defaultValue) {
        int n;
        for (; ; ) {
            String str = readKeyBoard(10, true);

            if (str.equals("")){
                n = defaultValue;
                break;
            }else {
                try {
                    n = Integer.parseInt(str);
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("数字输入错误，请重新输入：");
                }
            }

        }
        return n;

    }

    /**
     从键盘读取一个长度不超过limit的字符串，并将其作为方法的返回值。
     * @return
     */
    public static String readString(int limit) {
        String str = readKeyBoard(limit,false);
        return str;
    }

    /**
     从键盘读取一个长度不超过limit的字符串，如果回车不做修改并将其作为方法的返回值。
     */
    public static String readStringU(int limit, String defaultValue) {
        String str = readKeyBoard(limit,true);
        return str.equals("")? defaultValue : str;
    }

    /**
     用于确认选择的输入。该方法从键盘读取‘Y’或’N’，并将其作为方法的返回值。
     */
    public static char readConfirmSelection() {
        char c;
        System.out.print("确认：Y/y 取消：N/n\n");
        for (; ; ) {
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("选择错误，请重新输入：");
            }
        }
        return c;
    }

    /**
     * 保证double有效性
     * @return
     */
    public static double parseDouble() {
        String value = null;
        try {
            value = scanner.next();
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("不正确的值: " + value+"              管理员注意，我们需要的是数字！！");
        }
    }


    private static String readKeyBoard(int limit, boolean blankReturn) {
        String line = "";

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.length() == 0) {
                if (blankReturn) return line;
                else continue;
            }

            if (line.length() < 1 || line.length() > limit) {
                System.out.print("输入长度（不大于" + limit + "）错误，请重新输入：");
                continue;
            }
            break;
        }

        return line;
    }

}
