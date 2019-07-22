package com.nb.java.point_study.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *@Author feri
 *@Date Created in 2019/7/17 11:33
 */
@Component
public class JavaTask {
    @Scheduled(cron = "0/10 * * * * ?")
    public void daymsg(){
        //b12d46180eda262ec3a1cec558aa950e
        String s=Http_Util.getStr("http://v.juhe.cn/joke/randJoke.php?key=b12d46180eda262ec3a1cec558aa950e");
        System.out.println(s);
    }
}
