package book.toby1.user.dao;

import book.toby1.user.domain.Level;
import book.toby1.user.domain.User;
import book.toby1.user.sql.SimpleSqlService;
import book.toby1.user.sql.SqlService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.test.annotation.DirtiesContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@SpringBootTest
//@ContextConfiguration(locations = "/test-applicationContext.xml")
@DirtiesContext
public class UserDaoJdbcTest {
    //    @Autowired
    private UserDao dao;
    private DataSource dataSource;
    private SqlService sqlService;
    private Map<String, String> sqlMap;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp() {
        sqlMap = new HashMap<>();
        sqlMap.put("add", "insert into users(id, name, password, email, level, login, recommend) values(?, ?, ?, ?, ?, ?, ?)");
        sqlMap.put("get", "select * from users where id = ?");
        sqlMap.put("getAll", "select * from users order by id");
        sqlMap.put("deleteAll", "delete from users");
        sqlMap.put("getCount", "select count(*) from users");
        sqlMap.put("update", "update users set name=?, password=?, email=?, level=?, login=?, recommend=? where id=?");

        // test db를 따로 DI 가능
        this.dataSource = new SingleConnectionDataSource("jdbc:mysql://localhost:3306/toby", "root", "mysql", true);
        this.sqlService = new SimpleSqlService(sqlMap);
        dao = new UserDaoJdbc(dataSource, sqlService);

        dao.deleteAll();

        user1 = new User("1", "AAA", "aaa", "aaa@aaa", Level.BASIC, 1, 0);
        user2 = new User("2", "BBB", "bbb", "bbb@bbb", Level.SILVER, 55, 10);
        user3 = new User("3", "CCC", "ccc", "ccc@ccc", Level.GOLD, 100, 40);
    }

    @Test
    void addAndGetTest() {
        dao.add(user1);
        dao.add(user2);

        User findUser1 = dao.get(user1.getId());
        checkSameUser(findUser1, user1);
        User findUser2 = dao.get(user2.getId());
        checkSameUser(findUser2, user2);
    }

    @Test
    @Disabled
    void countingConnectionMakerTest() {
        dao.add(user1);

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDaoJdbc dao = applicationContext.getBean("userDao", UserDaoJdbc.class);
        dao.setSqlService(sqlService);

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
        UserDao dao = applicationContext.getBean("userDao", UserDaoJdbc.class);

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
        assertThat(user1.getLevel()).isEqualTo(user2.getLevel());
        assertThat(user1.getLogin()).isEqualTo(user2.getLogin());
        assertThat(user1.getRecommend()).isEqualTo(user2.getRecommend());
    }

    @Test
    void getAllLengthZero() {
        List<User> users = dao.getAll();
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(0);
    }

    @Test
    public void duplicateKeyErrorTest() {
        dao.add(user1);
//        DataAccessException.class
        Assertions.assertThrows(DuplicateKeyException.class, () -> dao.add(user1));
    }

    @Test
    public void sqlExceptionTranslate() {
        try {
            dao.add(user1);
            dao.add(user1);
        } catch (DuplicateKeyException e) {
            SQLException sqlEx = (SQLException) e.getRootCause();
            SQLErrorCodeSQLExceptionTranslator set = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
            assertThat(set.translate(null, null, sqlEx)).isInstanceOf(DuplicateKeyException.class);
        }
    }

    @Test
    public void updateTest() {
        dao.add(user1);
        dao.add(user2);

        user1.setName("QQQ");
        user1.setPassword("qqq");
        user1.setEmail("fff@fff");
        user1.setLevel(Level.GOLD);
        user1.setLogin(1000);
        user1.setRecommend(999);
        dao.update(user1);

        User findUser1 = dao.get(user1.getId());
        checkSameUser(findUser1, user1);

        User findUser2 = dao.get(user2.getId());
        checkSameUser(findUser2, user2); // update에 where 절이 정상 동작하는지 테스트
    }
}
