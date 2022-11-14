package book.toby1.user.dao;

import book.toby1.user.domain.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    User get(String id);

    List<User> getAll();

    void deleteAll();

    Integer getCount();

    void update(User user);
}
