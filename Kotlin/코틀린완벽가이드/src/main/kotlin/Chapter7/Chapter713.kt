fun chapter713() {
    val seq = sequenceOf(1, 2, 3) // lazy, like lambda

    println(seq.iterator().next()) // 1
    println(listOf(10, 20, 30).asSequence().iterator().next()) // 10
    println(mapOf(1 to 10, 2 to 20, 3 to 30).asSequence().iterator().next()) // 1=10

    val powers = generateSequence(1) { it * 2 } // 무한 시퀀스
//    powers.forEach {
//        println(it) // 무한 출력
//    }

    val numbers = sequence {
        yield(0)
        yieldAll(listOf(1, 2, 3))
        yieldAll(intArrayOf(4, 5, 6).iterator())
        yieldAll(generateSequence(10) { if (it < 50) it * 3 else null })
    }
    println(numbers.toList()) // [0, 1, 2, 3, 4, 5, 6, 10, 30, 90]

    val s = arrayOf(1, 2, 3)
    val toS = s.toList() // 복사
    val asS = s.asList() // view 생성

    s[0] = 5

    println(toS) // [1, 2, 3]
    println(asS) // [5, 2, 3]
}
