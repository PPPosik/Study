package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {
    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        log.info("call logic1");
        log.info("resultTime={}", System.currentTimeMillis() - startTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        log.info("call logic2");
        log.info("resultTime={}", System.currentTimeMillis() - startTime);
    }

    @Test
    void templateMethodV1() {
        AbstractTemplate template1 = new SubClassLogic1();
        AbstractTemplate template2 = new SubClassLogic2();

        template1.execute();
        template2.execute();
    }
}
