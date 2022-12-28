fun chapter441() {
    // val app = Application(name)
    val app = Application.Factory.create(arrayOf("name"))

    println(app?.name)
}

class Application private constructor(val name: String) {
    object Factory: FactoryParent() {
        override fun create(args: Array<String>): Application? {
            val name = args.firstOrNull() ?: return null
            return Application(name)
        }
    }
}

abstract class FactoryParent {
    abstract fun create(args: Array<String>): Application?
}