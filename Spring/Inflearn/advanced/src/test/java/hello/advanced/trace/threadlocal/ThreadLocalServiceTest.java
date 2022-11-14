package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class ThreadLocalServiceTest {
    private ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    void field() throws InterruptedException {
        log.info("main start");
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Runnable userA = new Worker(countDownLatch, threadLocalService, "userA");
        Runnable userB = new Worker(countDownLatch, threadLocalService, "userB");

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        threadB.start();

        countDownLatch.await();
        log.info("main end");
    }

    private static class Worker implements Runnable {
        private CountDownLatch countDownLatch;
        private ThreadLocalService threadLocalService;
        private String name;

        public Worker(CountDownLatch countDownLatch, ThreadLocalService threadLocalService, String name) {
            this.countDownLatch = countDownLatch;
            this.threadLocalService = threadLocalService;
            this.name = name;
        }

        @Override
        public void run() {
            threadLocalService.logic(name);
            countDownLatch.countDown();
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
