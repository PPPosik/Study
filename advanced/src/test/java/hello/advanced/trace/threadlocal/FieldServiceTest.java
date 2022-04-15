package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class FieldServiceTest {
    private FieldService fieldService = new FieldService();

    @Test
    void field() throws InterruptedException {
        log.info("main start");
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Runnable userA = new Worker(countDownLatch, fieldService, "userA");
        Runnable userB = new Worker(countDownLatch, fieldService, "userB");

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(2000); // 동시성 문제 X, 주석을 치면 동시성 문제 발생
        threadB.start();

        countDownLatch.await();
        log.info("main end");
    }

    private static class Worker implements Runnable {
        private CountDownLatch countDownLatch;
        private FieldService fieldService;
        private String name;

        public Worker(CountDownLatch countDownLatch, FieldService fieldService, String name) {
            this.countDownLatch = countDownLatch;
            this.fieldService = fieldService;
            this.name = name;
        }

        @Override
        public void run() {
            fieldService.logic(name);
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
