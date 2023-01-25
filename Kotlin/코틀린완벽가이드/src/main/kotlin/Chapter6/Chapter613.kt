fun chapter613() {
    println(Ordinal.FIRST.name)
    println(Ordinal.FIRST.ordinal)

    println(Ordinal.FIRST < Ordinal.SECOND)
    println(Ordinal.valueOf("THIRD").ordinal)
    println(Ordinal.values().joinToString(", "))
}

enum class Ordinal {
    FIRST, SECOND, THIRD
}