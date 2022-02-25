package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = applicationContext.getBean(StatefulService.class);
        StatefulService statefulService2 = applicationContext.getBean(StatefulService.class);

        int price1 = statefulService1.order("AAA", 10000);
        int price2 = statefulService2.order("BBB", 15000);

//        System.out.println("statefulService1.getPrice() = " + statefulService1.getPrice());
//        System.out.println("statefulService2.getPrice() = " + statefulService2.getPrice());

//        assertThat(statefulService1.getPrice()).isEqualTo(15000);
        assertThat(price1).isEqualTo(10000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}