package book.toby1.user.service;

import book.toby1.user.domain.User;

public interface UserService {
    void add(User user);

    void upgradeLevels();
}
