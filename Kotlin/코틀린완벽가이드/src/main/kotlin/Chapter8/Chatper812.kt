fun chapter812() {
    Student812("name", 1, "university").showInfo()
    /*
        name, 1, null
        name, 1, university
    */
}

open class Person812(val name: String, val age: Int) {
    open fun showInfo() {
        println("$name, $age")
    }

    init {
        showInfo() // name, 1, null
    }
}

class Student812(name: String, age: Int, val university: String) : Person812(name ,age) {
    override fun showInfo() {
        println("$name, $age, $university")
    }
}