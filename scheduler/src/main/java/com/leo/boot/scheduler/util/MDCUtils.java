package com.leo.boot.scheduler.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.CollectionUtils;

import java.util.Map;

public abstract class MDCUtils {

    private static final Logger logger = LoggerFactory.getLogger(MDCUtils.class);

    public static Runnable decorate(Runnable runnable) {
        logger.info("submit");
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return () -> {
            if (!CollectionUtils.isEmpty(contextMap)) {
                MDC.setContextMap(contextMap);
            }
            try {
                logger.info("begin");
                runnable.run();
            } finally {
                logger.info("end");
                MDC.clear();
            }
        };
    }
}
