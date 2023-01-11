fun chapter521() {
    val s = readLine()
    println(s.truncate(3)) // ?. 을 사용하지 않아도 된다
}

fun String?.truncate(maxLength: Int): String? {
    if (this == null) {
        return null
    } else {
        return if (length <= maxLength) this else substring(0, maxLength)
    }
}