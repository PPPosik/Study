fun chapter711() {
    val p1 = ComparablePerson("a", "b", 0)
    val p2 = ComparablePerson("b", "c", 0)

    println(p1.compareTo(p2))
    println(p2.compareTo(p1))
}

class ComparablePerson(
    val firstName: String,
    val lastName: String,
    val age: Int,
) : Comparable<ComparablePerson> {
    val fullName get() = "$firstName $lastName"
    override fun compareTo(other: ComparablePerson): Int = fullName.compareTo(other.fullName)

    val AGE_COMPARATOR = Comparator<ComparablePerson> { p1, p2 ->
        p1.age.compareTo(p2.age)
    }
    val AGE_COMPARATOR2 = compareBy<ComparablePerson> { it. age }
    val REVERSE_AGE_COMPARATOR = compareByDescending<ComparablePerson> { it.age }
}

