fun chapter621() {
    val classPerson1 = ClassPerson("pposik", "p", 20)
    val classPerson2 = ClassPerson("pposik", "p", 20)
    val dataPerson1 = DataPerson("pposik", "p", 20)
    val dataPerson2 = DataPerson("pposik", "p", 20)

    println(classPerson1 == classPerson2) // false
    println(dataPerson1 == dataPerson2) // true

    println(classPerson1.toString())
    println(dataPerson1.toString())

    val person = DataPerson("pposik", "p", 30)

    person.show()
    person.copy(familyName = "ppp").show()
    person.copy(familyName = "ppp", age = 20).show()
}

class ClassPerson(
    val firstName: String,
    val familyName: String,
    val age: Int
) {}

// equals(), hashCode(), toString() 별도로 구현됨
data class DataPerson(
    val firstName: String,
    val familyName: String,
    val age: Int
)

fun DataPerson.show() = println("$firstName, $familyName: $age")