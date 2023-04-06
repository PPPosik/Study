@MyAnnotation1011("ttt", Dependency1011(), ["aa", "bb"])
fun chapter1011() {}

annotation class MyAnnotation1011(
    val text: String,
    val dependency: Dependency1011 = Dependency1011(),
    val names: Array<String>
) {
    companion object {
        val text = "text"
    }
}

annotation class Dependency1011