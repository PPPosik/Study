import kotlin.properties.Delegates.notNull
import kotlin.properties.Delegates.observable
import kotlin.properties.Delegates.vetoable

fun chapter1121() {
    var num1: Int by notNull() // lateinit error
    lateinit var num2: Number
    num1 = 10
    num2 = 20
    println("num1: $num1, num2: $num2")

    val person = Person1121("John")
    person.name = "Harry" // Name changed: John to Harry
    person.name = "Vincent" // Name changed: Harry to Vincent
    person.name = "Vincent" // Name changed: Vincent to Vincent

    var password: String by vetoable("password") { _, old, new ->
        if (new.length < 8) {
            println("Password should be at least 8 characters long")
            false
        } else {
            println("Password is OK")
            true
        }
    }
    password = "pAsSwOrD" // Password is OK
    println(password) // pAsSwOrD
    password = "qwerty" // Password should be at least 8 characters long
    println(password) // pAsSwOrD, not changed
}

class Person1121(name: String) {
    var name: String by observable(name) { _, old, new -> println("Name changed: $old to $new") }
}