package book.toby1;

import book.toby1.user.dao.UserDao;
import book.toby1.user.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Toby1Application {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SpringApplication.run(Toby1Application.class, args);

		UserDao dao = new UserDao();
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
