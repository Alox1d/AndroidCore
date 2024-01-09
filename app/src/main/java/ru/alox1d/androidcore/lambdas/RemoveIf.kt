package lambdas

fun main() {
    val a = arrayListOf(1, 2, 3)
    a.removeIf { it > 2 }
}