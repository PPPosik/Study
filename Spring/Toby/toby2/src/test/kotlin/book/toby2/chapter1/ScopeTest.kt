package book.toby2.chapter1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope

class ScopeTest {
    class SingletonBean {}
    class SingletonClientBean(
        @Autowired val bean1: SingletonBean,
        @Autowired val bean2: SingletonBean,
    ) {}

    @Test
    fun `Singleton Scope test`() {
        val ac = AnnotationConfigApplicationContext(SingletonBean::class.java, SingletonClientBean::class.java)
        val beans = HashSet<SingletonBean>()

        beans.add(ac.getBean(SingletonBean::class.java))
        beans.add(ac.getBean(SingletonBean::class.java))
        assertEquals(1, beans.size)

        beans.add(ac.getBean(SingletonClientBean::class.java).bean1)
        beans.add(ac.getBean(SingletonClientBean::class.java).bean2)
        assertEquals(1, beans.size)
    }

    @Scope("prototype")
    class PrototypeBean {}
    class PrototypeClientBean(
        @Autowired val bean1: PrototypeBean,
        @Autowired val bean2: PrototypeBean,
    ) {}

    @Test
    fun `Prototype Scope test`() {
        val ac = AnnotationConfigApplicationContext(PrototypeBean::class.java, PrototypeClientBean::class.java)
        val beans = HashSet<PrototypeBean>()

        beans.add(ac.getBean(PrototypeBean::class.java))
        assertEquals(1, beans.size)
        beans.add(ac.getBean(PrototypeBean::class.java))
        assertEquals(2, beans.size)

        beans.add(ac.getBean(PrototypeClientBean::class.java).bean1)
        assertEquals(3, beans.size)
        beans.add(ac.getBean(PrototypeClientBean::class.java).bean2)
        assertEquals(4, beans.size)
    }
}