package lambdas


fun interface Lambda {
    fun fooLambda(i: Int): Int
}

fun interface Lambda2 {
    fun fooLambda(i: Int, y: Int): Int
}

fun main() {
    val result = Lambda { i -> i + 1 }
    println(result.fooLambda(1))

    val result2 = Lambda2 { i, y ->
        // val cannot bi reassigned
        // ERROR i = 6
        println("result2: ")
        i + 1
    }
    println(result2.fooLambda(1, 2))
}