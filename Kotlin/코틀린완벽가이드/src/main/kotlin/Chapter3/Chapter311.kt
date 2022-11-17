import kotlin.math.PI

fun chapter311() {
    val area = circleArea(3.0)

    println(area) // () -> kotlin.Double
    println(area.invoke()) // 28.274333882308138

    val v = outer(2.0)

    println(v) // (kotlin.Int) -> (kotlin.Long) -> kotlin.Long
    println(v.invoke(2)) // (kotlin.Long) -> kotlin.Long
    println(v.invoke(3).invoke(5L)) // 10
}

fun circleArea(radius: Double) = { PI * radius * radius }

fun outer(r: Double) = fun(_: Int) = fun(_: Long) = 10L