fun chapter241() {
    val array = Array(10) { (it + 1) * (it + 1) }

    println(array.joinToString(" "))
}