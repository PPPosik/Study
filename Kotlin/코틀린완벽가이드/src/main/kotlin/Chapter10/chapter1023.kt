import kotlin.reflect.KProperty1
import kotlin.reflect.jvm.isAccessible

fun chapter1023() {
    val secretHolder = SecretHolder1023("secret111")
    val secretProperty =
        secretHolder::class.members.first { it.name == "secret" } as KProperty1<SecretHolder1023, String>

    secretProperty.isAccessible = true
    println(secretProperty.get(secretHolder)) // secret111
}

class SecretHolder1023(private val secret: String)