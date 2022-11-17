fun chapter314() {
    val a = intArrayOf(6, 2, 10, 1)

    printSorted(6, 2, 10, 1)
    printSorted(*a)
    println("chapter314 : ${a.contentToString()}") // [6, 2, 10, 1], 원본이 변경되지 않음
}

fun printSorted(vararg n: Int) {
    n.sort()
    println("printSorted : ${n.contentToString()}")
}