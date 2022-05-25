package book.toby1;

import book.toby1.user.dao.*;
import book.toby1.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

public class UserDaoTest {
    @Test
    void addAndFindTest() throws SQLException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao1 = applicationContext.getBean("userDao", UserDao.class);
        UserDao dao2 = applicationContext.getBean("userDao", UserDao.class);
        System.out.println("dao1 = " + dao1);
        System.out.println("dao2 = " + dao2);

        UserDao dao = applicationContext.getBean("userDao", UserDao.class);
        User user = new User();
        user.setId("1");
        user.setName("AAA");
        user.setPassword("aaa");

        dao.add(user);
        System.out.println(user.getId() + " 등록");

        User findUser = dao.get(user.getId());
        System.out.println("findUser.getName() = " + findUser.getName());
        System.out.println("findUser.getPassword() = " + findUser.getPassword());
    }

    @Test
    void countingConnectionMakerTest() throws SQLException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CountingDaoFactory.class);

        UserDao dao = applicationContext.getBean("userDao", UserDao.class);
        dao.get("1");
        dao.get("1");
        dao.get("1");

        CountingConnectionMaker connectionMaker = applicationContext.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("connectionMaker.getCounter() = " + connectionMaker.getCounter());
    }

    @Test
    void xmlConfigurationTest() throws SQLException {
        ApplicationContext applicationContext = new GenericXmlApplicationContext("applicationContext.xml");

        UserDao dao = applicationContext.getBean("userDao", UserDao.class);
        dao.get("1");
    }
}
