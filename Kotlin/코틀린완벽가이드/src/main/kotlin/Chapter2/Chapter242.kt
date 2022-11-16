fun chapter242() {
    val array1 = intArrayOf(1, 2, 3) + 4
    val array2 = intArrayOf(1, 2, 3) + intArrayOf(4, 5)

    println(array1.joinToString(" "))
    println(array2.joinToString(" "))

    println(array1 == intArrayOf(1, 2, 3, 4)) // false
    println(array1.contentEquals(intArrayOf(1, 2, 3, 4))) // true
}