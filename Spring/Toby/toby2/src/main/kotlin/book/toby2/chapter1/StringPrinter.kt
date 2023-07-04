package book.toby2.chapter1

class StringPrinter(
    private val buffer: StringBuffer = StringBuffer()
) : Printer {
    override fun print(message: String) {
        buffer.append(message)
    }

    override fun toString(): String = buffer.toString()
}