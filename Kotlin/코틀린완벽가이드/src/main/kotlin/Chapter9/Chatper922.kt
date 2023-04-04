fun chapter922() {
    val numbers = ListByArray922<Number>(1, 1.25, 3f)
    val integers = ListByArray922(10, 20, 30)
    val result = concat(numbers, integers) // covariant
}

interface List922<out T> {
    val size: Int
    fun get(index: Int): T
}

class ListByArray922<T>(private vararg val items: T) : List922<T> {
    override val size: Int
        get() = items.size

    override fun get(index: Int): T = items[index]
}

fun <T> concat(list1: List922<T>, list2: List922<T>) = object : List922<T> {
    override val size: Int
        get() = list1.size + list2.size

    override fun get(index: Int): T {
        return if (index < list1.size) {
            list1.get(index)
        } else {
            list2.get(index - list1.size)
        }
    }
}