fun chapter7110() {
    println(List(6) { it * it }.take(2)) // [0, 1]
    println(List(6) { it * it }.drop(2)) // [4, 9, 16, 25]
    println(List(10) { it * it }.chunked(3) { it.sum() }) // [5, 50, 149, 81]
    println(List(6) { it * it }.windowed(3)) // [[0, 1, 4], [1, 4, 9], [4, 9, 16], [9, 16, 25]]
    println(List(6) { it * it }.zipWithNext()) // [(0, 1), (1, 4), (4, 9), (9, 16), (16, 25)]
}
