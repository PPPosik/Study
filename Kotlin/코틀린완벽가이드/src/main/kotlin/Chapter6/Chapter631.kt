fun chapter631() {
    println(Dollar(15).add(Dollar(20)).amount)
    println(Dollar(-100).isDebt)

    println(Dollar(15).amount) // inline O, type: Dollar
    println(Dollar(15)) // inline X, type: Any?
    println(safeAmount(Dollar(15))) // inline X, type: Dollar?
}

fun safeAmount(dollar: Dollar?) = dollar?.amount ?: 0

@JvmInline
value class Dollar(val amount: Int) {
    fun add(d: Dollar) = Dollar(amount + d.amount)
    val isDebt get() = amount < 0
}
