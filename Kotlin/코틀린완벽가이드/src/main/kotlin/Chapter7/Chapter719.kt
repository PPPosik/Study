fun chapter719() {
    println(setOf("A", "BB", "CCC").map { it.length }) // [1, 2, 3]
    println(listOf(1, 2, 3).mapNotNull { it * it }) // [1, 4, 9]

    println(mapOf("one" to 1, "two" to 2).mapKeys { it.key.uppercase() }) // {ONE=1, TWO=2}
    println(mapOf("one" to 1, "two" to 2).mapValues { it.value.toString(2) }) // {one=1, two=10}

    val result = ArrayList<String>()
    listOf(1, 2, 3).mapTo(result) { it.toString() }
    arrayOf("one", "two", "three").mapIndexedTo(result) { i, s -> "${i + 1} $s" }
    sequenceOf("100", "?", "101", "?").mapNotNullTo(result) { it.toIntOrNull(2)?.toString() }
    println(result) // [1, 2, 3, 1 one, 2 two, 3 three, 4, 5]

    println(setOf("abc", "def", "ghi").flatMap { it.asIterable() }) // [a, b, c, d, e, f, g, h, i]
    println(Array(3) { it + 1 }.flatMap { 1..it }) // [1, 1, 2, 1, 2, 3]

    println(listOf(listOf(1, 2), setOf(3, 4), listOf(5)).flatten()) // [1, 2, 3, 4, 5]

    println(listOf("red", "green", "blue").associateWith { it.length }) // {red=3, green=5, blue=4}
    println(listOf("red", "green", "blue").associateBy { it.length }) // {3=red, 5=green, 4=blue}
    println(listOf("red", "green", "blue").associate { it.uppercase() to it.length }) // {RED=3, GREEN=5, BLUE=4}
}
