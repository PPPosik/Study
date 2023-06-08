package book.toby1.learningtest;

import book.toby1.TestApplicationContext;
import book.toby1.learningtest.factoryBean.Message;
import book.toby1.learningtest.factoryBean.MessageFactoryBean;
import book.toby1.learningtest.proxy.Hello;
import book.toby1.learningtest.proxy.HelloTarget;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationContext.class)
public class FactoryBeanTest {
    @Autowired
    ApplicationContext context;

    @Test
    public void getMessageFromFactoryBean() {
        Object message = context.getBean("message");

        assertEquals(Message.class, message.getClass());
        assertEquals("Factory Bean", ((Message) message).getText());
    }

    @Test
    public void getFactoryBean() {
        Object factory = context.getBean("&message");

        assertEquals(MessageFactoryBean.class, factory.getClass());
    }

    @Test
    public void springProxyFactoryBean() {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());
        pfBean.addAdvice(new UppercaseAdvice());

        Hello proxiedHello = (Hello) pfBean.getObject();

        assertNotNull(proxiedHello);
        assertEquals("HELLO PPOSIK", proxiedHello.sayHello("pposik"));
        assertEquals("HI PPOSIK", proxiedHello.sayHi("pposik"));
        assertEquals("THANK YOU PPOSIK", proxiedHello.sayThankYou("pposik"));
    }

    static class UppercaseAdvice implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            String ret = (String) invocation.proceed();
            return ret.toUpperCase();
        }
    }

    @Test
    public void pointcutAdvisor() {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("sayH*");

        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));

        Hello proxiedHello = (Hello) pfBean.getObject();

        assertNotNull(proxiedHello);
        assertEquals("HELLO PPOSIK", proxiedHello.sayHello("pposik"));
        assertEquals("HI PPOSIK", proxiedHello.sayHi("pposik"));
        assertEquals("Thank You pposik", proxiedHello.sayThankYou("pposik"));
    }

}
