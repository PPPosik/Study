fun chapter412() {
    Person("pposik p").printFullName()
    Person("pposik", "p").printFullName()
}

class Person(fullName: String) {
    private var firstName: String
    private var familyName: String

    constructor(firstName: String, familyName: String) : this("$firstName $familyName") {
        this.firstName = firstName
        this.familyName = familyName
    }

    init {
        val names = fullName.split(" ")

        if (names.size != 2) {
            throw IllegalArgumentException("Invalid Name")
        }

        firstName = names[0]
        familyName = names[1]
    }

    fun printFullName() = println("$firstName $familyName")
}