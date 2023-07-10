package book.toby2.chapter1

import book.toby2.chapter1.config.HelloQualifierConfig
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@Import(HelloQualifierConfig::class)
class HelloWithQualifierConfigTest {
    @Autowired
    private lateinit var hello: Hello

    @Autowired
    private lateinit var stringPrinter: StringPrinter

    @Autowired
    private lateinit var consolePrinter: ConsolePrinter

    @Test
    fun `Value test`() {
        assertEquals(consolePrinter, hello.printer)
        assertNotEquals(stringPrinter, hello.printer)
    }

}