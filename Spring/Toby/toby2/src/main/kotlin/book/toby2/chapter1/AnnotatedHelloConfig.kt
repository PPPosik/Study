package book.toby2.chapter1

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AnnotatedHelloConfig {
    @Bean
    fun annotatedHelloByConfig() = AnnotatedHello()
}