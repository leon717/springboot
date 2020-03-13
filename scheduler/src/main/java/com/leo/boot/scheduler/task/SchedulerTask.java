package com.leo.boot.scheduler.task;

import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class SchedulerTask {

    @Scheduled(cron = "*/5 * * * * ?")
    public void cron() {
        System.out.println("SchedulerTask.cron:" + new Date());
    }

    @Scheduled(fixedRate = 5000)
    public void fixedRate() {
        System.out.println("SchedulerTask.fixedRate:" + new Date());
    }
}
