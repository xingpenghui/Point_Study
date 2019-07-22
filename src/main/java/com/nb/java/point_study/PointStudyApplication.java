package com.nb.java.point_study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //启动Spring Task
public class PointStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PointStudyApplication.class, args);
    }

}
