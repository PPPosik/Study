package book.toby2.chapter1.config

import book.toby2.chapter1.ConsolePrinter
import book.toby2.chapter1.Hello
import book.toby2.chapter1.Printer
import book.toby2.chapter1.StringPrinter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class HelloQualifierConfig {
    @Bean
    fun hello(@Qualifier("consolePrinter") printer: Printer) = Hello(printer)

    @Bean
    fun stringPrinter() = StringPrinter()

    @Bean
    fun consolePrinter() = ConsolePrinter()
}