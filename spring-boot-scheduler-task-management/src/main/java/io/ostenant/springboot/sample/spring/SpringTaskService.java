package io.ostenant.springboot.sample.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class SpringTaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringTaskService.class);
    private AtomicLong counter = new AtomicLong();

    @Scheduled(initialDelay = 2000L, fixedDelay = 10 * 1000L)
    public void scheduleWithFixedDelay() throws Exception {
        try {
            TimeUnit.MILLISECONDS.sleep(10 * 1000L);
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted exception", e);
        }
        long count = counter.incrementAndGet();
        LOGGER.info("Schedule executor {} times with fixed delay", count);
    }

    @Scheduled(fixedRate = 10 * 1000L)
    public void scheduleAtFixedRate() throws Exception {
        long count = counter.incrementAndGet();
        LOGGER.info("Schedule executor {} times at fixed rate", count);
    }

    @Scheduled(cron = "0/10 * * * * *")
    public void scheduleWithCronExpression() throws Exception {
        long count = counter.incrementAndGet();
        LOGGER.info("Schedule executor {} times with ", count);
    }
}
