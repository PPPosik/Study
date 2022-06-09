package book.toby1.learningtest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(locations = "/junit.xml")
public class JUnitTest {
    @Autowired
    ApplicationContext context;

    static Set<JUnitTest> testObjects = new HashSet<>();
    static ApplicationContext contextObject = null;

    @Test
    @DisplayName("JUnit은 테스트 메서드를 수행할 때마다 새로운 오브젝트를 생성")
    void test1() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);

        if (contextObject != null) {
            assertThat(contextObject).isNotNull().isEqualTo(contextObject);
        }
        contextObject = this.context;
    }

    @Test
    @DisplayName("JUnit은 테스트 메서드를 수행할 때마다 새로운 오브젝트를 생성")
    void test2() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);

        if (contextObject != null) {
            assertThat(contextObject).isNotNull().isEqualTo(contextObject);
        }
        contextObject = this.context;
    }

    @Test
    @DisplayName("JUnit은 테스트 메서드를 수행할 때마다 새로운 오브젝트를 생성")
    void test3() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);

        if (contextObject != null) {
            assertThat(contextObject).isNotNull().isEqualTo(contextObject);
        }
        contextObject = this.context;
    }
}
