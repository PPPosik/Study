fun chapter1117() {
    val (from, to) = Range1117(10, 20)

    println("from: $from, to: $to")
}

class Range1117(
    private val from: Int,
    private val to: Int,
) {
    operator fun component1() = from
    operator fun component2() = to
}