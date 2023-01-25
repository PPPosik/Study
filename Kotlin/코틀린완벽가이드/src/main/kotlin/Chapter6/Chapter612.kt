fun chapter612() {
    println(RainbowColor.BLUE.isCold)
    println(RainbowColor.RED.isWarm)
}

enum class RainbowColor(val isCold: Boolean) {
    RED(false),
    ORANGE(false),
    YELLOW(false),
    GREEN(true),
    BLUE(true),
    INDIGO(true),
    VIOLET(true);

    val isWarm get() = !isCold
}