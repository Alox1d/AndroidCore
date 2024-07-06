package string

fun main() {
    foo(1.toString())
    val rawText = "Hello string".format("string")
    println(rawText)
}

fun foo(s: String) {
    println(s)
}