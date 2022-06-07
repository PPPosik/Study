package book.toby1;

import book.toby1.user.dao.*;
import book.toby1.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserDaoTest {
    @Autowired
    ApplicationContext applicationContext;

    private UserDao dao;
    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() throws SQLException {
        System.out.println("applicationContext = " + applicationContext);
        System.out.println("this = " + this);
        dao = applicationContext.getBean("userDao", UserDao.class);
        dao.deleteAll();

        user1 = new User("1", "AAA", "aaa");
        user2 = new User("2", "BBB", "bbb");
    }

    @Test
    void addAndGetTest() throws SQLException {
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

        dao.add(user1);
        dao.get("1");
        dao.get("1");
        dao.get("1");

        CountingDataSource countingDataSource = applicationContext.getBean("countingDataSource", CountingDataSource.class);
        System.out.println("countingDataSource = " + countingDataSource);
        assertThat(countingDataSource.getCounter()).isEqualTo(4);
    }

    @Test()
    void getFail() {
        assertThrows(EmptyResultDataAccessException.class, () -> dao.get("1"));
    }

    @Test
    void xmlConfigurationTest() throws SQLException {
        ApplicationContext applicationContext = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao dao = applicationContext.getBean("userDao", UserDao.class);

        dao.add(user1);
        dao.get("1");
    }

    @Test
    void deleteTest() throws SQLException {
        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);
    }

    @Test
    void getCountTest() throws SQLException {
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);
    }
}
