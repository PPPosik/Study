package book.toby1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = { TestApplicationContext.class, AppContext.class})
class Toby1ApplicationTests {

	@Test
	void contextLoads() {
	}

}
