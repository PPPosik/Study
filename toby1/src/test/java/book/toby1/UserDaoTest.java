package book.toby1;

import book.toby1.user.dao.DConnectionMaker;
import book.toby1.user.dao.DaoFactory;
import book.toby1.user.dao.UserDao;
import book.toby1.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoTest {
    @Test
    void addAndFindTest() throws SQLException, ClassNotFoundException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao1 = applicationContext.getBean("userDao", UserDao.class);
        UserDao dao2 = applicationContext.getBean("userDao", UserDao.class);
        System.out.println("dao1 = " + dao1);
        System.out.println("dao2 = " + dao2);

        UserDao dao = applicationContext.getBean("userDao", UserDao.class);
        User user = new User();
        user.setId("1");
        user.setName("AAA");
        user.setPassword("aaa");

        dao.add(user);
        System.out.println(user.getId() + " 등록");

        User findUser = dao.get(user.getId());
        System.out.println("findUser.getName() = " + findUser.getName());
        System.out.println("findUser.getPassword() = " + findUser.getPassword());
    }
}
