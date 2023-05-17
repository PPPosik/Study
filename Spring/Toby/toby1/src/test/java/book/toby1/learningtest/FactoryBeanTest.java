package book.toby1.learningtest;

import book.toby1.learningtest.factoryBean.Message;
import book.toby1.learningtest.factoryBean.MessageFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
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
}
