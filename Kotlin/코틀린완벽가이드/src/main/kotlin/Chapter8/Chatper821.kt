fun chapter821() {
    Amphibia821().move() // car move
}

interface Car821 {
    fun move() {
        println("car move")
    }
}

interface Ship821 {
    fun move() {
        println("ship move")
    }
}

class Amphibia821 : Car821, Ship821 {
    override fun move() {
        super<Car821>.move()
    }
}