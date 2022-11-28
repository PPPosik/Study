fun chapter425() {
    val retNull = fun() = null

    println(retNull()?.toInt() ?: 0)
    println(Person425(Name425("pposik", "p")).describe())
    println(Person425(null).describe())
}

class Name425(val firstName: String, val familyName: String)

class Person425(val name: Name425?) {
    fun describe(): String {
        val currentName = name ?: return "Unknown"
        return "${currentName.firstName} ${currentName.familyName}"
    }
}