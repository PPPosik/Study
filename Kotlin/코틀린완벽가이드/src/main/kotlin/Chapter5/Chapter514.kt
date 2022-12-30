fun chapter514() {
    println(check("Hello") {isCapitalLetter(it)})
    println(check("Hello", ::isCapitalLetter))

    class Car(val name: String) {
        fun nameEquals(name: String) = name == this.name
    }

    val createCar = ::Car
    val car = createCar("AAA")
    val isPPosikCar = Car("PPosik")::nameEquals

    println(car.name)
    println(isPPosikCar("PPosik"))
}

fun check(s: String, condition: (Char) -> Boolean): Boolean {
    for (c in s) {
        if (!condition(c)) {
            return false
        }
    }

    return true
}

fun isCapitalLetter(c: Char) = c.isUpperCase() && c.isLetter()