package book.toby1.user.sql;

import java.util.Map;

public class SimpleSqlService implements SqlService {
    private Map<String, String> sqlMap;

    public SimpleSqlService() {

    }

    public SimpleSqlService(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }

    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {
        String sql = sqlMap.get(key);

        if (sql == null) {
            throw new SqlRetrievalFailureException("can not find sql for key : " + key);
        } else {
            return sql;
        }
    }
}
