fun chapter1022() {
    val personClass = Class.forName("Person1022").kotlin
    val person = personClass.constructors.first().call("John", "Doe")
    val firstName = personClass.members.first { it.name == "firstName" }
    val fullNameFun = personClass.members.first { it.name == "fullName" }

    println(firstName.call(person)) // John
    println(fullNameFun.call(person, false)) // John Doe
}

class Person1022(val firstName: String, val familyName: String) {
    fun fullName(familyFirst: Boolean) = if (familyFirst) {
        "$familyName $firstName"
    } else {
        "$firstName $familyName"
    }
}