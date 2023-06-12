package book.toby1;

import book.toby1.learningtest.factoryBean.MessageFactoryBean;
import book.toby1.user.dao.UserDao;
import book.toby1.user.service.DummyMailSender;
import book.toby1.user.service.UserService;
import book.toby1.user.service.UserServiceTest.TestUserService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;

@Configuration
@Profile("test")
public class TestApplicationContext {
    @Bean
    public MailSender mailSender() {
        return new DummyMailSender();
    }

    @Bean
    public UserService testUserService(UserDao userDao, MailSender mailSender) {
        return new TestUserService();
    }

    @Bean
    public FactoryBean message() {
        MessageFactoryBean factoryBean = new MessageFactoryBean();

        factoryBean.setText("Factory Bean");

        return factoryBean;
    }
}
