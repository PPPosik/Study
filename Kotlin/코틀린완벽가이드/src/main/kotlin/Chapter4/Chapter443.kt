fun chapter443() {
    fun midPoint(x: IntRange, y: IntRange) = object {
        val x = (x.first + x.last) / 2
        val y = (y.first + y.last) / 2
    } // 함수 정의가 바깥 scope이면 return을 인지하지 못해서 아래에서 Any로 받게됨, print 에러남

    val point = midPoint(1..5, 2..6)

    println("(${point.x}, ${point.y})") // (3, 4)

    var x = 1
    val o = object {
        val a = x++
    }

    println("x = $x") // 2, 객체가 lazy 초기화 되지 않음
    println("o.a = ${o.a}") // 1
}