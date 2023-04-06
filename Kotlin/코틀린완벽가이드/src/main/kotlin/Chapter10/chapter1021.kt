import kotlin.reflect.KClass

fun chapter1021() {
    val component = Main1021::class.annotations
        .filterIsInstance<Component1021>()
        .firstOrNull() ?: return

    println("Component name: ${component.name}") // Component name: Core

    val depText = component.dependency.componentClasses.joinToString { it.simpleName ?: "" }

    println("Dependencies: $depText") // Dependencies: IO1021, Logger1021
}

annotation class Dependency1021(vararg val componentClasses: KClass<*>)

annotation class Component1021(
    val name: String = "Core",
    val dependency: Dependency1021 = Dependency1021()
)

@Component1021("I/O")
class IO1021

@Component1021("Log", Dependency1021(IO1021::class))
class Logger1021

@Component1021(dependency = Dependency1021(IO1021::class, Logger1021::class))
class Main1021