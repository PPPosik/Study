package book.toby1.user.dao;

public class DaoFactory {
    public UserDao userDao() {
        return new UserDao(new DConnectionMaker());
    }
}
