package book.toby2.chapter1.config

import book.toby2.chapter1.Hello
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class HelloValueConfig {
    @Bean
    fun hello() = Hello()
}