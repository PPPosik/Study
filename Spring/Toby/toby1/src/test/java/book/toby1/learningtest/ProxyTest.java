package book.toby1.learningtest;

import book.toby1.learningtest.proxy.Hello;
import book.toby1.learningtest.proxy.HelloTarget;
import book.toby1.learningtest.proxy.HelloUppercase;
import book.toby1.learningtest.proxy.UppercaseHandler;
import org.junit.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProxyTest {
    @Test
    public void simpleProxy() {
        Hello hello = new HelloTarget();

        assertEquals("Hello pposik", hello.sayHello("pposik"));
        assertEquals("Hi pposik", hello.sayHi("pposik"));
        assertEquals("Thank You pposik", hello.sayThankYou("pposik"));
    }

    @Test
    public void simpleProxyDecorator() {
        Hello hello = new HelloTarget();
        Hello proxiedHello = new HelloUppercase(hello);

        assertEquals("HELLO PPOSIK", proxiedHello.sayHello("pposik"));
        assertEquals("HI PPOSIK", proxiedHello.sayHi("pposik"));
        assertEquals("THANK YOU PPOSIK", proxiedHello.sayThankYou("pposik"));
    }

    @Test
    public void dynamicProxyHandler() {
        Hello proxiedHello = (Hello) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{Hello.class},
                new UppercaseHandler(new HelloTarget())
        );

        assertNotNull(proxiedHello);
        assertEquals("HELLO PPOSIK", proxiedHello.sayHello("pposik"));
        assertEquals("HI PPOSIK", proxiedHello.sayHi("pposik"));
        assertEquals("THANK YOU PPOSIK", proxiedHello.sayThankYou("pposik"));
    }

    @Test
    public void classNamePointcutAdvisor() {
        NameMatchMethodPointcut classMethodPointcut = new NameMatchMethodPointcut() {
            public ClassFilter getClassFilter() {
                return new ClassFilter() {
                    public boolean matches(Class<?> clazz) {
                        return clazz.getSimpleName().startsWith("HelloP") || clazz.getSimpleName().startsWith("HelloT");
                    }
                };
            }
        };
        classMethodPointcut.setMappedNames("sayH*");

        checkAdviced(new HelloTarget(), classMethodPointcut, true);

        class HelloWorld extends HelloTarget {}
        checkAdviced(new HelloWorld(), classMethodPointcut, false);

        class HelloPposik extends HelloTarget {}
        checkAdviced(new HelloPposik(), classMethodPointcut, true);
    }

    private void checkAdviced(Object target, Pointcut pointcut, boolean adviced) {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(target);
        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new FactoryBeanTest.UppercaseAdvice()));
        Hello proxiedHello = (Hello) pfBean.getObject();

        if (adviced) {
            assertEquals("HELLO PPOSIK", proxiedHello.sayHello("pposik"));
            assertEquals("HI PPOSIK", proxiedHello.sayHi("pposik"));
            assertEquals("Thank You pposik", proxiedHello.sayThankYou("pposik"));
        } else {
            assertEquals("Hello pposik", proxiedHello.sayHello("pposik"));
            assertEquals("Hi pposik", proxiedHello.sayHi("pposik"));
            assertEquals("Thank You pposik", proxiedHello.sayThankYou("pposik"));
        }
    }
}
