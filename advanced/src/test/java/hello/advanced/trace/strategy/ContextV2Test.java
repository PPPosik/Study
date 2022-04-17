package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV2;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {
    @Test
    void strategyV1() {
        new ContextV2().execute(new StrategyLogic1());
        new ContextV2().execute(new StrategyLogic2());
    }

    @Test
    void strategyV2() {
        new ContextV2().execute(() -> log.info("call logic1"));
        new ContextV2().execute(() -> log.info("call logic2"));
    }
}
