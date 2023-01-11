fun chapter551() {
    Address().run {
        zipCode = 123456
        city = "London"
        street = "Baker Street"
        house = "221B"

        post("run")
    } // return Unit

    val message = with(Address(city = "London", street = "Baker Street", house = "221B")) {
        "with : $city, $street, $house"
    }
    println(message)

    Address(city = "London", street = "Baker Street", house = "221B")?.let {
        it.post("let")
    }

    Address().apply {
        city = "London"
        street = "Baker Street"
        house = "221B"
    }.post("apply")

    Address().also {
        it.city = "London"
        it.street = "Baker Street"
        it.house = "221B"
    }.post("also")
}

class Address(
    var zipCode: Int = 0,
    var city: String = "",
    var street: String = "",
    var house: String = ""
) {
    fun post(message: String) = println("Message for {$zipCode, $city, $street, $house} : $message")
}