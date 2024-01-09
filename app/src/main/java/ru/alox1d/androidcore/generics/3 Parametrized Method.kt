package generics

class ParametrizedMethod {
    companion object {
        fun <T> getSecondElement(list: List<T>): T {
            return list[1]
        }
    }
}

fun main() {
    val a = ParametrizedMethod.getSecondElement(listOf(1, 2, 3))
    println(a)
}