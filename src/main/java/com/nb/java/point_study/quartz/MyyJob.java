package com.nb.java.point_study.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *@Author feri
 *@Date Created in 2019/7/17 10:15
 */
public class MyyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("重要的事情说三遍");
    }
}
