fun chapter313() {
    // override : 파라미터 개수와 타입 -> 덜 구체적인 함수 제외 -> 결과가 하나일 경우 실행, 아니면 에러

    println(mul(1, 2)) // 1, 4 중 1 실행 (덜 구체적인 4 제외)
    println(mul("0", 3)) // 3, 4 중 3 실행 (덜 구체적인 4 제외)
    println(mul("0" as Any, 3)) // 4 중 4 실행
    println(mul(1L, 3)) // 4 중 4 실행
}

fun mul(a: Int, b: Int) = a * b // 1
fun mul(a: Int, b: Int, c: Int) = a * b * c // 2
fun mul(s: String, n: Int) = s.repeat(n) // 3
fun mul(o: Any, n: Int) = Array(n) { o } // 4