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
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@SpringBootTest
//@ContextConfiguration(locations = "/test-applicationContext.xml")
@DirtiesContext
public class UserDaoTest {
    //    @Autowired
    private UserDao dao;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp() {
        dao = new UserDao();

        // test db를 따로 DI 가능
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource("jdbc:mysql://localhost:3306/toby", "root", "mysql", true);
        dao.setDataSource(dataSource);

        dao.deleteAll();

        user1 = new User("1", "AAA", "aaa");
        user2 = new User("2", "BBB", "bbb");
        user3 = new User("3", "CCC", "ccc");
    }

    @Test
    void addAndGetTest() {
        dao.add(user1);
        dao.add(user2);

        User findUser1 = dao.get(user1.getId());
        assertThat(findUser1).isNotNull();
        assertThat(findUser1.getId()).isEqualTo(user1.getId());
        assertThat(findUser1.getPassword()).isEqualTo(user1.getPassword());
        assertThat(findUser1.getName()).isEqualTo(user1.getName());

        User findUser2 = dao.get(user2.getId());
        assertThat(findUser2).isNotNull();
        assertThat(findUser2.getId()).isEqualTo(user2.getId());
        assertThat(findUser2.getPassword()).isEqualTo(user2.getPassword());
        assertThat(findUser2.getName()).isEqualTo(user2.getName());
    }

    @Test
    void countingConnectionMakerTest() {
        dao.add(user1);

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDao dao = applicationContext.getBean("userDao", UserDao.class);

        dao.get("1");
        dao.get("1");
        dao.get("1");

        CountingDataSource countingDataSource = applicationContext.getBean("countingDataSource", CountingDataSource.class);
        System.out.println("countingDataSource = " + countingDataSource);
        assertThat(countingDataSource.getCounter()).isEqualTo(3);
    }

    @Test()
    void getFail() {
        assertThrows(EmptyResultDataAccessException.class, () -> dao.get("1"));
    }

    @Test
    void xmlConfigurationTest() {
        ApplicationContext applicationContext = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao dao = applicationContext.getBean("userDao", UserDao.class);

        dao.add(user1);
        dao.get("1");
    }

    @Test
    void deleteTest() {
        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);
    }

    @Test
    void getCountTest() {
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);
    }

    @Test
    void getAll() {
        dao.add(user1);
        List<User> users1 = dao.getAll();
        assertThat(users1.size()).isEqualTo(1);
        checkSameUser(user1, users1.get(0));

        dao.add(user2);
        List<User> users2 = dao.getAll();
        assertThat(users2.size()).isEqualTo(2);
        checkSameUser(user1, users2.get(0));
        checkSameUser(user2, users2.get(1));

        dao.add(user3);
        List<User> users3 = dao.getAll();
        assertThat(users3.size()).isEqualTo(3);
        checkSameUser(user1, users3.get(0));
        checkSameUser(user2, users3.get(1));
        checkSameUser(user3, users3.get(2));
    }

    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId()).isEqualTo(user2.getId());
        assertThat(user1.getName()).isEqualTo(user2.getName());
        assertThat(user1.getPassword()).isEqualTo(user2.getPassword());
    }

    @Test
    void getAllLengthZero() {
        List<User> users = dao.getAll();
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(0);
    }
}
