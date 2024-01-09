package sealed_classes

sealed interface Error // has implementations only in same package and module
{
    object A : Error
    class B(val b: Int = 0) : Error
    data class C(val c: Int = 0) : Error
}

fun main() {
    val error: Error = Error.B()
    when (error) {
        Error.A -> TODO()
        is Error.B -> TODO()
        is Error.C -> TODO()
        is CustomError -> TODO()
    }
}
