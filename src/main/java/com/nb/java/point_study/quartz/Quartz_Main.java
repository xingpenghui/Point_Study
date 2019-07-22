package com.nb.java.point_study.quartz;

import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

/**
 *@Author feri
 *@Date Created in 2019/7/17 10:23
 */
public class Quartz_Main {
    public static void main(String[] args) throws SchedulerException {
        //1、定义触发器 3秒执行一次  建造者模式

        Trigger trigger=TriggerBuilder.newTrigger().withSchedule(
                SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(3).repeatForever()).build();
        //2、创建作业详情
        JobDetail jobDetail=JobBuilder.newJob(MyyJob.class).build();
        //3、创建调度器
        Scheduler scheduler=new StdSchedulerFactory().getScheduler();
        //4、设置 定时作业
        scheduler.scheduleJob(jobDetail,trigger);
        //5、启动调度器
        scheduler.start();
//        scheduler.shutdown();
//        scheduler.pauseJob();
//        scheduler.resumeJob();
    }
}
