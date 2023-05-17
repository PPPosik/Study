package book.toby1.learningtest;

import book.toby1.learningtest.proxy.Hello;
import book.toby1.learningtest.proxy.HelloTarget;
import book.toby1.learningtest.proxy.HelloUppercase;
import book.toby1.learningtest.proxy.UppercaseHandler;
import org.junit.Test;

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
}
