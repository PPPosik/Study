package book.toby1.user.dao;

import book.toby1.user.domain.Level;
import book.toby1.user.domain.User;
import book.toby1.user.sql.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDaoJdbc implements UserDao {
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userMapper = (rs, rowNum) -> new User(
            rs.getString("id"),
            rs.getString("name"),
            rs.getString("password"),
            rs.getString("email"),
            Level.valueOf(rs.getInt("level")),
            rs.getInt("login"),
            rs.getInt("recommend"));
    private SqlService sqlService;

    public UserDaoJdbc() {
    }

    public UserDaoJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public UserDaoJdbc(DataSource dataSource, SqlService sqlService) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.sqlService = sqlService;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    @Override
    public void add(final User user) {
        this.jdbcTemplate.update(sqlService.getSql("add"), user.getId(), user.getName(), user.getPassword(), user.getEmail(), user.getLevel().intValue(), user.getLogin(), user.getRecommend());
    }

    // 예외를 전환해서 사용
    // SQLException은 JDBC를 통해 런타임 예외인 DataAccessException으로 포장되어 던져짐
//    public void addExceptionExample(final User user) throws DuplicateUserIdException {
//        try {
//            // SQLException이 발생할 수 있는 코드
//        } catch (SQLException e) {
//            if (e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY) {
//                throw new DuplicateUserIdException(e);
//            } else {
//                throw e;
//            }
//        }
//    }

    @Override
    public User get(String id) {
        return this.jdbcTemplate.queryForObject(sqlService.getSql("get"), userMapper, id);
    }

    @Override
    public List<User> getAll() {
        return this.jdbcTemplate.query(sqlService.getSql("getAll"), userMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(sqlService.getSql("deleteAll"));
    }

    @Override
    public Integer getCount() {
        return this.jdbcTemplate.queryForObject(sqlService.getSql("getCount"), Integer.class);
    }

    @Override
    public void update(User user) {
        this.jdbcTemplate.update(sqlService.getSql("update"), user.getName(), user.getPassword(), user.getEmail(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(), user.getId());
    }
}
