package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {
    @Test
    void reflection0() {
        Hello target = new Hello();

        log.info("start1");
        String result1 = target.callA();
        log.info("end1");

        log.info("start2");
        String result2 = target.callB();
        log.info("end2");
    }

    @Test
    void reflection1() throws Exception {
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        Method callA = classHello.getMethod("callA");
        Object result1 = callA.invoke(target);
        log.info("result1={}", result1);

        Method callB = classHello.getMethod("callB");
        Object result2 = callB.invoke(target);
        log.info("result2={}", result2);
    }

    @Test
    void reflection2() throws Exception {
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        String result1 = (String) dynamicCall(classHello.getMethod("callA"), new Hello());
        log.info("result1={}", result1);

        String result2 = (String) dynamicCall(classHello.getMethod("callB"), new Hello());
        log.info("result2={}", result2);
    }

    private Object dynamicCall(Method method, Object target) throws InvocationTargetException, IllegalAccessException {
        log.info("start dynamicCall");
        Object result = method.invoke(target);
        log.info("end dynamicCall");
        return result;
    }

    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
