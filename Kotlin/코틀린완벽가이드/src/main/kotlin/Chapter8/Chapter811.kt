fun chapter811() {
    val v: Vehicle811 = Car811()

    v.start() // car start
    v.stop() // vehicle stop
}

open class Vehicle811 {
    open fun start() {
        println("vehicle start")
    }
}

fun Vehicle811.stop() {
    println("vehicle stop")
}

class Car811 : Vehicle811() {
    override fun start() {
        println("car start")
    }
}

fun Car811.stop() {
    println("car stop")
}