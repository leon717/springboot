package com.leo.boot.scheduler.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.scheduling.support.CronSequenceGenerator;

public class CornPaser {

    public static void main(String[] args) {
        getNextExecTime("*/6 * * * * ?", 10).forEach(System.out::println);
    }

    public static List<Date> getNextExecTime(String cron, Integer times) {
        CronSequenceGenerator generator = new CronSequenceGenerator(cron);
        ArrayList<Date> result = new ArrayList<>();
        Date temp = new Date();
        for (int i = 0; i < times; i++) {
            temp = generator.next(temp);
            result.add(temp);
        }
        return result;
    }

}
