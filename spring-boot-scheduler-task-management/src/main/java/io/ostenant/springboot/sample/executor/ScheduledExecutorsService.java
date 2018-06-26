package io.ostenant.springboot.sample.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ScheduledExecutorsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledExecutorsService.class);
    private AtomicLong counter = new AtomicLong();


    public void scheduleWithFixedDelay() {
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(4);
        for (int i = 0; i < 4; i++) {
            scheduledExecutor.scheduleWithFixedDelay(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10 * 1000L);
                } catch (InterruptedException e) {
                    LOGGER.error("Interrupted exception", e);
                }
                long count = counter.incrementAndGet();
                LOGGER.info("Schedule executor {} times with fixed delay", count);
            }, 2000L, 10 * 1000L, TimeUnit.MILLISECONDS);
        }
        LOGGER.info("Start to schedule");
    }

    public void scheduleAtFixedRate() {
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(4);
        for (int i = 0; i < 4; i++) {
            scheduledExecutor.scheduleAtFixedRate(() -> {
                long count = counter.incrementAndGet();
                LOGGER.info("Schedule executor {} times at fixed rate", count);
            }, 2000L, 10 * 1000L, TimeUnit.MILLISECONDS);
        }
        LOGGER.info("Start to schedule");
    }

    public static void main(String[] args) {
        ScheduledExecutorsService service = new ScheduledExecutorsService();
        service.scheduleWithFixedDelay();
    }
}
