package book.toby1;

import book.toby1.user.dao.*;
import book.toby1.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserDaoTest {
    private UserDao dao;

    @BeforeEach
    public void setUp() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        dao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    void addAndGetTest() throws SQLException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao1 = applicationContext.getBean("userDao", UserDao.class);
        UserDao dao2 = applicationContext.getBean("userDao", UserDao.class);
        System.out.println("dao1 = " + dao1);
        System.out.println("dao2 = " + dao2);

        UserDao dao = applicationContext.getBean("userDao", UserDao.class);
        User user1 = new User("1", "AAA", "aaa");
        User user2 = new User("2", "BBB", "bbb");

        dao.deleteAll();

        dao.add(user1);
        dao.add(user2);

        User findUser1 = dao.get(user1.getId());
        assertThat(findUser1.getId()).isEqualTo(user1.getId());
        assertThat(findUser1.getPassword()).isEqualTo(user1.getPassword());
        assertThat(findUser1.getName()).isEqualTo(user1.getName());

        User findUser2 = dao.get(user2.getId());
        assertThat(findUser2.getId()).isEqualTo(user2.getId());
        assertThat(findUser2.getPassword()).isEqualTo(user2.getPassword());
        assertThat(findUser2.getName()).isEqualTo(user2.getName());
    }

    @Test
    void countingConnectionMakerTest() throws SQLException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CountingDaoFactory.class);

        UserDao dao = applicationContext.getBean("userDao", UserDao.class);
        User user = new User("1", "AAA", "aaa");

        dao.deleteAll();
        dao.add(user);

        dao.get("1");
        dao.get("1");
        dao.get("1");

        CountingConnectionMaker connectionMaker = applicationContext.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("connectionMaker.getCounter() = " + connectionMaker.getCounter());
    }

    @Test()
    void getFail() throws SQLException {
        ApplicationContext applicationContext = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao dao = applicationContext.getBean("userDao", UserDao.class);

        dao.deleteAll();

        assertThrows(EmptyResultDataAccessException.class, () -> dao.get("1"));
    }

    @Test
    void xmlConfigurationTest() throws SQLException {
        ApplicationContext applicationContext = new GenericXmlApplicationContext("applicationContext.xml");

        UserDao dao = applicationContext.getBean("userDao", UserDao.class);
        User user = new User("1", "AAA", "aaa");

        dao.deleteAll();
        dao.add(user);
        dao.get("1");
    }

    @Test
    void deleteTest() throws SQLException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao = applicationContext.getBean("userDao", UserDao.class);
        User user = new User("1", "AAA", "aaa");

        dao.deleteAll();

        dao.add(user);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);
    }

    @Test
    void getCountTest() throws SQLException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao = applicationContext.getBean("userDao", UserDao.class);

        dao.deleteAll();

        assertThat(dao.getCount()).isEqualTo(0);
        User user1 = new User("1", "AAA", "aaa");
        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);
        User user2 = new User("2", "BBB", "bbb");
        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);
    }
}
