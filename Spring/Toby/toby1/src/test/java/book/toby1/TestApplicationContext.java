package book.toby1;

import book.toby1.learningtest.factoryBean.MessageFactoryBean;
import book.toby1.user.dao.UserDao;
import book.toby1.user.dao.UserDaoJdbc;
import book.toby1.user.service.DummyMailSender;
import book.toby1.user.service.UserService;
import book.toby1.user.service.UserServiceImpl;
import book.toby1.user.service.UserServiceTest.TestUserService;
import book.toby1.user.sql.SimpleSqlService;
import book.toby1.user.sql.SqlService;
import com.mysql.cj.jdbc.Driver;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mail.MailSender;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class TestApplicationContext {
    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/toby");
        dataSource.setUsername("root");
        dataSource.setPassword("mysql");

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();

        tm.setDataSource(dataSource);

        return tm;
    }

    @Bean
    public MailSender mailSender() {
        return new DummyMailSender();
    }

    @Bean
    public SqlService sqlService() {
        SimpleSqlService sqlService = new SimpleSqlService();
        Map<String, String> sqlMap = new HashMap<>();

        sqlMap.put("add", "insert into users(id, name, password, email, level, login, recommend) values(?, ?, ?, ?, ?, ?, ?)");
        sqlMap.put("get", "select * from users where id = ?");
        sqlMap.put("getAll", "select * from users order by id");
        sqlMap.put("deleteAll", "delete from users");
        sqlMap.put("getCount", "select count(*) from users");
        sqlMap.put("update", "update users set name=?, password=?, email=?, level=?, login=?, recommend=? where id=?");
        sqlService.setSqlMap(sqlMap);

        return sqlService;
    }

    @Bean
    public UserDao userDao(DataSource dataSource, SqlService sqlService) {
        UserDaoJdbc userDao = new UserDaoJdbc();

        userDao.setDataSource(dataSource);
        userDao.setSqlService(sqlService);

        return userDao;
    }

    @Bean
    public UserService userService(UserDao userDao, MailSender mailSender) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.setUserDao(userDao);
        userService.setMailSender(mailSender);

        return userService;
    }

    @Bean
    public UserService testUserService(UserDao userDao, MailSender mailSender) {
        TestUserService testUserService = new TestUserService();

        testUserService.setUserDao(userDao);
        testUserService.setMailSender(mailSender);

        return testUserService;
    }

    @Bean
    public FactoryBean message() {
        MessageFactoryBean factoryBean = new MessageFactoryBean();

        factoryBean.setText("Factory Bean");

        return factoryBean;
    }
}
