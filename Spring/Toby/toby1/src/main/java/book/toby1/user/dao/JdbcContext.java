package book.toby1.user.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void executeSql(final String query) throws SQLException {
        workWithStatementStrategy(connection -> connection.prepareStatement(query));
    }

    public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = stmt.makePreparedStatement(connection)) {
            ps.executeUpdate();
        }
    }
}
