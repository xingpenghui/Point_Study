package com.nb.java.point_study.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 *@Author feri
 *@Date Created in 2019/7/17 11:17
 */
public class Quartz_CRON_Main {
    public static void main(String[] args) throws SchedulerException {
        //1、定义触发器 3秒执行一次  建造者模式
        Trigger trigger=TriggerBuilder.newTrigger().withSchedule(
                CronScheduleBuilder.cronSchedule("0/5 20/1 11 17 * ?")).build();
        //2、创建作业详情
        JobDetail jobDetail=JobBuilder.newJob(HelloJob.class).build();
        //传递数据
        JobDataMap jobDataMap=jobDetail.getJobDataMap();
        jobDataMap.put("str","java1902");

        //3、创建调度器
        Scheduler scheduler=new StdSchedulerFactory().getScheduler();
        //4、设置 定时作业
        scheduler.scheduleJob(jobDetail,trigger);
        //5、启动调度器
        scheduler.start();
    }
}
