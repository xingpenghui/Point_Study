package com.nb.java.point_study.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *@Author feri
 *@Date Created in 2019/7/17 11:17
 */
public class HelloJob implements Job {
    private String s;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("磊哥说：多敲代码，练手速");
        JobDataMap jobDataMap=jobExecutionContext.getJobDetail().getJobDataMap();
        s=jobDataMap.getString("str");
        System.out.println(s+"---->"+System.currentTimeMillis());

    }
}
