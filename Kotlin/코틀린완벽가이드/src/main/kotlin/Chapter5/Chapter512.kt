fun chapter512() {
    val consumer = object : StringConsumer {
        override fun accept(s: String) {
            println(s)
        }

        fun measureTime(action: () -> Unit): Long {
            val start = System.nanoTime()

            action()

            return System.nanoTime() - start
        }
    }

    consumer.accept("consumer")
    consumer.measureTime { println("measureTime") }

    val lessThan = { a: Int, b: Int -> a < b }
    println(lessThan(1, 2))
}

fun interface StringConsumer { // SAM interface
    fun accept(s: String)
}