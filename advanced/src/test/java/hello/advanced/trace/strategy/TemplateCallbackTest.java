package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.template.Callback;
import hello.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {
    @Test
    void callbackV1() {
        TimeLogTemplate template1 = new TimeLogTemplate();
        template1.execute(new Callback() {
            @Override
            public void call() {
                log.info("call logic1");
            }
        });

        TimeLogTemplate template2 = new TimeLogTemplate();
        template2.execute(new Callback() {
            @Override
            public void call() {
                log.info("call logic2");
            }
        });
    }

    @Test
    void callbackV2() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(() -> log.info("call logic1"));
        template.execute(() -> log.info("call logic2"));
    }
}
