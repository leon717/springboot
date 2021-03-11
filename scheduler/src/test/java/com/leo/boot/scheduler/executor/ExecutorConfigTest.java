package com.leo.boot.scheduler.executor;

import com.leo.boot.scheduler.util.MDCUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import static com.leo.boot.scheduler.util.MDCUtils.decorate;

@SpringBootTest(classes = ExecutorConfig.class)
@RunWith(SpringRunner.class)
public class ExecutorConfigTest {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfigTest.class);

    @Test
    public void test() throws ExecutionException, InterruptedException {
        String key = "key";
        String value = "value";
        MDC.put(key, value);

        Executors.newSingleThreadExecutor().submit(() -> {
            logger.info("");
            Assert.assertNull(MDC.get(key));
        }).get();

        Executors.newSingleThreadExecutor().submit(decorate(() -> {
            logger.info("");
            Assert.assertEquals(value, MDC.get(key));
        })).get();

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setTaskDecorator(MDCUtils::decorate);
        taskExecutor.initialize();
        taskExecutor.submit(() -> {
            logger.info("");
            Assert.assertEquals(value, MDC.get(key));
        }).get();
    }
}