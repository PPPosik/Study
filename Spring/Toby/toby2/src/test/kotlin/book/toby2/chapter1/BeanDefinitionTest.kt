package book.toby2.chapter1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.config.RuntimeBeanReference
import org.springframework.beans.factory.support.RootBeanDefinition
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.GenericXmlApplicationContext
import org.springframework.context.support.StaticApplicationContext

class BeanDefinitionTest {
    @Test
    fun `BeanDefinition test`() {
        val ac = StaticApplicationContext()
        ac.registerSingleton("hello1", Hello::class.java)

        val helloDef = RootBeanDefinition(Hello::class.java)
        // <property name="name" value="Spring" />
        helloDef.propertyValues.addPropertyValue("name", "Spring")
        ac.registerBeanDefinition("hello2", helloDef)

        val hello1 = ac.getBean("hello1", Hello::class.java)
        val hello2 = ac.getBean("hello2", Hello::class.java)

        assertNotNull(hello1)
        assertNotNull(hello2)
        assertNotEquals(hello1, hello2)
        assertEquals("Hello Spring", hello2.sayHello())
        assertEquals(2, ac.beanFactory.beanDefinitionCount)
    }

    @Test
    fun `BeanDefinition dependency test`() {
        val ac = StaticApplicationContext()
        ac.registerBeanDefinition("printer", RootBeanDefinition(StringPrinter::class.java))

        val helloDef = RootBeanDefinition(Hello::class.java)
        helloDef.propertyValues.addPropertyValue("name", "Spring")
        helloDef.propertyValues.addPropertyValue("printer", RuntimeBeanReference("printer"))
        ac.registerBeanDefinition("hello", helloDef)

        val hello = ac.getBean("hello", Hello::class.java)
        hello.print()

        assertEquals(hello.printer, ac.getBean("printer"))
        assertEquals("Hello Spring", ac.getBean("printer").toString())
    }

    @Test
    fun `GenericApplicationContext test`() {
        val ac = GenericApplicationContext()
        val reader = XmlBeanDefinitionReader(ac)

        reader.loadBeanDefinitions("genericApplicationContext.xml")
        ac.refresh()

        val hello = ac.getBean("hello", Hello::class.java)
        hello.print()

        assertEquals(hello.printer, ac.getBean("printer"))
        assertEquals("Hello Spring", ac.getBean("printer").toString())
    }

    @Test
    fun `GenericXmlApplicationContext test`() {
        val ac = GenericXmlApplicationContext("genericApplicationContext.xml")

        val hello = ac.getBean("hello", Hello::class.java)
        hello.print()

        assertEquals(hello.printer, ac.getBean("printer"))
        assertEquals("Hello Spring", ac.getBean("printer").toString())
    }

    @Test
    fun `Parent Child Context test`() {
        val parent = GenericXmlApplicationContext("parentContext.xml")
        val child = GenericApplicationContext(parent)
        val reader = XmlBeanDefinitionReader(child);

        reader.loadBeanDefinitions("childContext.xml")
        child.refresh()

        val hello = child.getBean("hello", Hello::class.java)
        val printer = child.getBean("printer", Printer::class.java)
        hello.print()

        assertNotNull(hello)
        assertNotNull(printer)
        assertEquals("Hello Child", printer.toString())
    }

    @Test
    fun `AnnotationConfigApplicationContext test`() {
        val ctx = AnnotationConfigApplicationContext("book.toby2.chapter1")
        val hello = ctx.getBean("annotatedHello", AnnotatedHello::class.java)

        assertNotNull(hello)
        assertEquals("hello", hello.sayHello())
    }
}