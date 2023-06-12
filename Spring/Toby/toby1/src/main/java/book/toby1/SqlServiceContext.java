package book.toby1;

import book.toby1.user.sql.SimpleSqlService;
import book.toby1.user.sql.SqlService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SqlServiceContext {
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
}
