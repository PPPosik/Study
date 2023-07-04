package book.toby2.chapter1

class Hello(
    var name: String,
    var printer: Printer
) {
    constructor() : this("default", ConsolePrinter())

    fun sayHello() = "Hello $name"

    fun print() = printer.print(sayHello())
}