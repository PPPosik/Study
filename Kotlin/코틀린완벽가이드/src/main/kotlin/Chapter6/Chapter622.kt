fun chapter622() {
    val human = Human("pposik", "p", 20)

    val (firstName, lastName, age) = human
    println("$firstName, $lastName, $age")

    val (a, _, b) = human
    println("$a, $b")
}

data class Human(
    val firstName: String,
    val lastName: String,
    val age: Int
)