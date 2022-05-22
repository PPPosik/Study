package book.toby1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Toby1Application {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SpringApplication.run(Toby1Application.class, args);
	}

}
