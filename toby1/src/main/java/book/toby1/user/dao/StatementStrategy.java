package book.toby1.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
    PreparedStatement makePrepatedStatement(Connection c) throws SQLException;
}