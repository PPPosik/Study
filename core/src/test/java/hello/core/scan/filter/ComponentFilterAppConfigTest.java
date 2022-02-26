package hello.core.scan.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {
    @Test
    public void filterScan() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = applicationContext.getBean(BeanA.class);

        assertThat(beanA).isNotNull();
        assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(BeanB.class));
    }

    @Configuration
    @ComponentScan(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class), excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class))
    static class ComponentFilterAppConfig {
    }
}
