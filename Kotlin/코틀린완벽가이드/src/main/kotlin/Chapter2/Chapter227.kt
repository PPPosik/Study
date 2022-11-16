fun chapter227() {
    val x = 1
    val y = 1

    // eager - and, or, xor
    println((x == 1) and (y == 1))
    println((x == 1) or (y == 1))
    println((x == 1) xor (y == 1))

    // lazy - &&, ||
    println((x == 1) && (y == 1))
    println((x == 1) || (y == 1))

    println(x == 1 || y/(x - 1) != 1) // true, lazy 연산이 필수적임
    println(x == 1 or y/(x - 1)) // divide by zero error, eager 연산으로 인해 발생
}