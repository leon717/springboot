package com.leo.boot.scheduler.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public abstract class TimeStatUtils {

    private static final Logger logger = LoggerFactory.getLogger(TimeStatUtils.class);

    public static Runnable decorate(Runnable runnable) {
        logger.info("submit at {}.", new Date());
        return () -> {
            long beginTime = System.currentTimeMillis();
            logger.info("begin at {}.", new Date(beginTime));
            try {
                runnable.run();
            } finally {
                logger.info("end at {}, spent {} ms.", new Date(), System.currentTimeMillis() - beginTime);
            }
        };
    }
}
