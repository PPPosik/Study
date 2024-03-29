package book.toby1.user.service;

import book.toby1.AppContext;
import book.toby1.TestApplicationContext;
import book.toby1.user.dao.UserDao;
import book.toby1.user.domain.Level;
import book.toby1.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = { TestApplicationContext.class, AppContext.class })
public class UserServiceTest {
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserService testUserService;

    @Autowired
    private UserDao userDao;

    List<User> users;

    @Before
    public void setUp() {
        users = Arrays.asList(
                new User("aaa", "AAA", "p1", "aaa@aaa", Level.BASIC, UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER - 1, 0),
                new User("bbb", "BBB", "p2", "bbb@bbb", Level.BASIC, UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER, 0),
                new User("ccc", "CCC", "p3", "ccc@ccc", Level.SILVER, 60, UserServiceImpl.MIN_RECCOMEND_FOR_GOLD - 1),
                new User("ddd", "DDD", "p4", "ddd@ddd", Level.SILVER, 60, UserServiceImpl.MIN_RECCOMEND_FOR_GOLD),
                new User("eee", "EEE", "p5", "eee@eee", Level.GOLD, 100, 100)
        );
    }

    @Test
    public void bean() {
        assertNotNull(this.userService);
    }

    @Test
    public void upgradeLevels() {
        UserServiceImpl userServiceImpl = new UserServiceImpl();

        UserDao mockUserDao = mock(UserDao.class);
        when(mockUserDao.getAll()).thenReturn(this.users);
        userServiceImpl.setUserDao(mockUserDao);

        MailSender mockMailSender = mock(MailSender.class);
        userServiceImpl.setMailSender(mockMailSender);

        userServiceImpl.upgradeLevels();

        verify(mockUserDao, times(2)).update(any(User.class));
        verify(mockUserDao).update(users.get(1));
        verify(mockUserDao).update(users.get(3));
        checkUserAndLevel(users.get(1), "bbb", Level.SILVER);
        checkUserAndLevel(users.get(3), "ddd", Level.GOLD);

        ArgumentCaptor<SimpleMailMessage> mailMessageArg = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mockMailSender, times(2)).send(mailMessageArg.capture()); // parameter verify

        List<SimpleMailMessage> mailMessages = mailMessageArg.getAllValues();
        assertEquals(users.get(1).getEmail(), Objects.requireNonNull(mailMessages.get(0).getTo())[0]);
        assertEquals(users.get(3).getEmail(), Objects.requireNonNull(mailMessages.get(1).getTo())[0]);
    }

    private void checkUserAndLevel(User updated, String expectedId, Level expectedLevel) {
        assertEquals(expectedId, updated.getId());
        assertEquals(expectedLevel, updated.getLevel());
    }

    @Test
    public void add() {
        userDao.deleteAll();

        User userWithLevel = users.get(4);
        User userWithoutLevel = users.get(0);
        userWithoutLevel.setLevel(null);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        assertEquals(Level.GOLD, userWithLevelRead.getLevel());
        assertEquals(Level.BASIC, userWithoutLevelRead.getLevel());
    }

    public static class TestUserService extends UserServiceImpl {
        private String id = "ddd";

        @Override
        protected void upgradeLevel(User user) {
            if (user.getId().equals(this.id)) {
                throw new TestUserServiceException();
            }

            super.upgradeLevel(user);
        }

        @Override
        public List<User> getAll() {
            for (User user : super.getAll()) {
                super.update(user);
            }

            return null;
        }
    }

    static class TestUserServiceException extends RuntimeException {
    }

    @Test
    @DirtiesContext
    public void upgradeAllOrNothing() {
        userDao.deleteAll();
        for (User user : users) {
            userDao.add(user);
        }

        assertThrows(TestUserServiceException.class, this.testUserService::upgradeLevels);
        checkLevelUpgraded(users.get(1), false);
    }

    private void checkLevelUpgraded(User user, boolean upgraded) {
        User userUpdate = userDao.get(user.getId());

        if (upgraded) {
            assertEquals(user.getLevel().nextLevel(), userUpdate.getLevel());
        } else {
            assertEquals(user.getLevel(), userUpdate.getLevel());
        }
    }

    @Test
    public void readOnlyTransactionAttribute() {
        // read only exception
        assertThrows(TransientDataAccessResourceException.class, testUserService::getAll);
    }

    @Test
    public void transactionSync() {
        userService.deleteAll();
        assertEquals(0, userDao.getCount());

        DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(txDefinition);

        try {
            userService.add(users.get(0));
            userService.add(users.get(1));
            assertEquals(2, userDao.getCount());
        } finally {
            transactionManager.rollback(status);
            assertEquals(0, userDao.getCount());
        }
    }

    @Test
    @Transactional
    public void transactionSync2() {
        userService.deleteAll();
        userService.add(users.get(0));
        userService.add(users.get(1));
    }
}