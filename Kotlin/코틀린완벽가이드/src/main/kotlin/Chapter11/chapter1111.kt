fun chapter1111() {
    println(!Color1111.RED) // CYAN
    println(!Color1111.BLUE) // YELLOW
}

enum class Color1111() {
    RED, GREEN, BLUE, CYAN, MAGENTA, YELLOW;

    operator fun not() = when(this) {
        RED -> CYAN
        GREEN -> MAGENTA
        BLUE -> YELLOW
        CYAN -> RED
        MAGENTA -> GREEN
        YELLOW -> BLUE
    }
}