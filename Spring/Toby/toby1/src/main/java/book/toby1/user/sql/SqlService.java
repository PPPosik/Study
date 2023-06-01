package book.toby1.user.sql;

public interface SqlService {
    String getSql(String key) throws SqlRetrievalFailureException;
}
