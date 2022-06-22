package book.toby1.user.dao;

import book.toby1.user.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userMapper = (rs, rowNum) -> new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));

    public UserDaoJdbc() {
    }

    public UserDaoJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(final User user) {
        this.jdbcTemplate.update("insert into users(id, name, password) values(?, ?, ?)", user.getId(), user.getName(), user.getPassword());
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

    public User get(String id) {
        return this.jdbcTemplate.queryForObject("select * from users where id = ?", userMapper, id);
    }

    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users order by id", userMapper);
    }

    public void deleteAll() {
        this.jdbcTemplate.update("delete from users");
    }

    public Integer getCount() {
        return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }
}
