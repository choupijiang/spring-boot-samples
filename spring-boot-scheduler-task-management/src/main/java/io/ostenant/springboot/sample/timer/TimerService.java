package io.ostenant.springboot.sample.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;


public class TimerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimerService.class);
    private AtomicLong counter = new AtomicLong();

    public void schedule() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                long count = counter.incrementAndGet();
                LOGGER.info("Schedule timerTask {} times", count);
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1000L, 10 * 1000L);
    }

    public static void main(String[] args) {
        TimerService timerService = new TimerService();
        timerService.schedule();
    }
}
