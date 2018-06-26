package io.ostenant.springboot.sample.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

@Component
public class AsyncExecutorTask extends AbstractTask {
    @Async("taskExecutor")
    public void doTaskOne() throws Exception {
        super.doTaskOne();
        out.println("任务一，当前线程：" + currentThread().getName());
    }

    @Async("taskExecutor")
    public void doTaskTwo() throws Exception {
        super.doTaskTwo();
        out.println("任务二，当前线程：" + currentThread().getName());
    }

    @Async("taskExecutor")
    public void doTaskThree() throws Exception {
        super.doTaskThree();
        out.println("任务三，当前线程：" + currentThread().getName());
    }
}
