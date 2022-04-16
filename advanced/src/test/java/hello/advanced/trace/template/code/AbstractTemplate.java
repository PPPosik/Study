package hello.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {
    public void execute() {
        long startTime = System.currentTimeMillis();
        call();
        log.info("resultTime={}", System.currentTimeMillis() - startTime);
    }

    abstract void call();
}
