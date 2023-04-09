fun chapter1112() {
    var color = RainbowColor1112.INDIGO

    println(++color) // VIOLET
    println(--color) // INDIGO
}

enum class RainbowColor1112 {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET;

    operator fun inc() = values[(ordinal + 1) % values.size]
    operator fun dec() = values[(ordinal + values.size - 1) % values.size]

    companion object {
        private val values = enumValues<RainbowColor1112>()
    }
}