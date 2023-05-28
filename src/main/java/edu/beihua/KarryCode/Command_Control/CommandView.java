package edu.beihua.KarryCode.Command_Control;

import edu.beihua.KarryCode.tools.AccountUtility;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;

public class CommandView {
    public static <T> boolean examine(T s){
        String ss=(String) s;
        if (new CommandView().Command(ss)==1){
            System.out.println("控制级主动中断");
            AccountUtility.readReturn();
            return true;
        }
        return false;
    }
    public int Command(String s){

        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        String jvmName = runtimeBean.getName();
        long pid = Long.parseLong(jvmName.split("@")[0]);
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadBean.getAllThreadIds();
        long totalCpuTime = 0;
        for (long id : threadIds) {
            totalCpuTime += threadBean.getThreadCpuTime(id);
        }
        double cpuUsage = totalCpuTime / (1000.0 * 1000.0 * 1000.0) / Runtime.getRuntime().availableProcessors();
        String []commands={"","help","/out","/pid","/occ"};
        String []interpretations={"","指令帮助","中断退出","当前进程的 PID","CPU 占用率"};
            if (s.charAt(0)=='/'){
                System.out.println("————控制版会话————");
                s=s.substring(1);
                switch (s){
                    case "out":{
                        System.out.println("中断退出");
                        return 1;
                    }
                    case "pid":{
                        System.out.println("当前进程的 PID 是：" + pid);
                        System.out.println("自动执行：");
                        return 1;
                    }
                    case "occ":{
                        System.out.println("当前进程的 CPU 占用率：" + cpuUsage + "%");
                        System.out.println("自动执行：");
                        return 1;
                    }
                    case "help":{
                        System.out.println("+——————————————————————————————————————————————————————+");
                        for (int i=1;i<commands.length;i++){
                            System.out.println("|         "+commands[i]+"            "+interpretations[i]);
                        }
                        System.out.println("+——————————————————————————————————————————————————————+");

                        System.out.println("自动执行：");
                        return 1;
                    }
                    default:{
                        System.out.println("错误的指令！");
                        System.out.println("自动执行：");
                        return 1;
                    }
                }
            }


        return 0;
    }
}
