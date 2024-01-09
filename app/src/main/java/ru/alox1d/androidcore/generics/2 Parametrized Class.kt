package generics

class Info<T>(private val value: T) {
    override fun toString(): String {
        return "{[ $value ]}"
    }
}

fun main() {
    val info1 = Info("Hello!")
    println(info1)
}