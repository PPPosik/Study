package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic {
    private ConcreteLogic concreteLogic;

    public TimeProxy(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    @Override
    public String operation() {
        log.info("TimeDecorator start");
        String result = concreteLogic.operation();
        String decoResult = "***** " + result + " *****";
        log.info("result={}, deco result={}", result, decoResult);
        log.info("TimeDecorator end");
        return decoResult;
    }
}
