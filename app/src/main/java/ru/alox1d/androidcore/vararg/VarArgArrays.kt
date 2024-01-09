package vararg

//    fun f (args: )
fun main() {
    main(Array<String>(1) { "S" })
}

fun main(args: Array<String>) {
    val list = listOf("args: ", *args)
    //Spread operator unpacks the   array contents
    println(list)
}
