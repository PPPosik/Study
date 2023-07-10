package book.toby2.chapter1

import org.springframework.beans.factory.annotation.Value

class Hello(
    var printer: Printer
) {
    @Value("pposik")
    var name: String = ""

    constructor() : this(ConsolePrinter())

    fun sayHello() = "Hello $name"

    fun print() = printer.print(sayHello())
}