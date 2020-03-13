package com.leo.boot.scheduler.task;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

@Component
public class DynamicTask {

    @Autowired
    private ThreadPoolTaskScheduler scheduler;

    private List<ScheduledFuture<?>> tasks = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    public void once() {
        scheduler.schedule(this::close, LocalDateTime.now().plusSeconds(10).atZone(ZoneId.systemDefault()).toInstant());
    }

    @PostConstruct
    public void corn() {
        ScheduledFuture<?> task = scheduler.schedule(() -> {
            System.out.println("DynamicTask.corn：" + new Date());
        }, new CronTrigger("*/5 * * * * *"));
        tasks.add(task);
    }

    @PostConstruct
    public void fixedRate() {
        ScheduledFuture<?> task = scheduler.schedule(() -> {
            System.out.println("DynamicTask.fixedRate：" + new Date());
        }, new PeriodicTrigger(5000));
        tasks.add(task);
    }

    public void close() {
        tasks.forEach(t -> t.cancel(true));
        System.out.println("DynamicTask.close");
    }

}
