fun chapter433() {
    val person = Person433("pposik", "p", 20)
    println(person.fullName)
    println(person.age)

    person.age = -100
    println(person.age)
}

class Person433(val firstName: String, val familyName: String, age: Int = 0) {
    val fullName: String
        get() = "$firstName $familyName"

    var age: Int = age
        get() {
            println("Accessing age")
            return field
        }
        set(value) {
            field = if (value < 0) {
                -1 * value
            } else {
                value
            }
        }
}