fun chapter1114() {
    val data = listOf("abc", "ABC", "", " ")

    println(data.count(String::isEmpty and String::isBlank)) // 1
    println(data.count(String::isEmpty or String::isBlank)) // 2
}

infix fun <T> ((T) -> Boolean).and(
    other: (T) -> Boolean
): (T) -> Boolean {
    return { this(it) && other(it) }
}

infix fun <T> ((T) -> Boolean).or(
    other: (T) -> Boolean
): (T) -> Boolean {
    return { this(it) || other(it) }
}