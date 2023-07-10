package book.toby2.chapter1

import book.toby2.chapter1.config.HelloValueConfig
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@Import(HelloValueConfig::class)
class HelloWithValueConfigTest {
    @Autowired
    private lateinit var hello: Hello

    @Test
    fun `Value test`() {
        assertEquals("Hello pposik", hello.sayHello())
    }

}