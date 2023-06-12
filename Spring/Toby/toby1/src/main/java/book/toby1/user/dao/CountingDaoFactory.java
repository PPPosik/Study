package book.toby1.user.dao;

import book.toby1.user.sql.SimpleSqlService;
import book.toby1.user.sql.SqlService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

// @Configuration
public class CountingDaoFactory {
    @Bean
    public UserDaoJdbc userDao() {
        return new UserDaoJdbc(countingDataSource(), sqlService());
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
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/toby");
        dataSource.setUsername("root");
        dataSource.setPassword("mysql");

        return dataSource;
    }

    @Bean
    public DataSource countingDataSource() {
        return new CountingDataSource(dataSource());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker(realConnectionMaker());
    }

    @Bean
    public ConnectionMaker realConnectionMaker() {
        return new DConnectionMaker();
    }
}
