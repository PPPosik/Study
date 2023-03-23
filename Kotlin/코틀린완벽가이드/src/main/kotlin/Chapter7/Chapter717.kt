fun chapter717() {
    val seq = sequenceOf(
        MinMaxPerson("Brook", "Watts", 25),
        MinMaxPerson("Silver", "Hudson", 30),
        MinMaxPerson("Dane", "Ortiz", 19),
        MinMaxPerson("Val", "Hall", 28),
    )

    println(seq.minByOrNull { it.firstName })
    println(seq.maxByOrNull { it.firstName })
    println(seq.minByOrNull { it.lastName })
    println(seq.maxByOrNull { it.lastName })
    println(seq.minByOrNull { it.age })
    println(seq.maxByOrNull { it.age })

    println(seq.minWithOrNull(MinMaxPerson.FULL_NAME_COMPARATOR))
    println(seq.maxWithOrNull(MinMaxPerson.FULL_NAME_COMPARATOR))

    println(listOf(1, 2, 3, 4, 5).reduce { acc, n -> acc * n }) // 120
    println(listOf("a", "b", "c", "d").reduce { acc, n -> acc + n }) // "abcd"
    println(listOf(1, 2, 3, 4).fold("") { acc, n -> acc + ('a' + n - 1) }) // "abcd"
}

class MinMaxPerson(
    val firstName: String,
    val lastName: String,
    val age: Int,
) {
    companion object {
        val FULL_NAME_COMPARATOR = Comparator<MinMaxPerson> { p1, p2 ->
            p1.fullName.compareTo(p2.fullName)
        }
    }

    val fullName get() = "$firstName $lastName"

    override fun toString() = "$firstName $lastName: $age"
}

