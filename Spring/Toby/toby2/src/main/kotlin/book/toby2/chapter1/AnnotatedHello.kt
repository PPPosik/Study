package book.toby2.chapter1

import org.springframework.stereotype.Component

@Component
class AnnotatedHello {
    fun sayHello() = "hello"
}