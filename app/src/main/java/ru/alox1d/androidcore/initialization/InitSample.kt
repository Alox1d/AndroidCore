package initialization

class InitSample(private val s1: String = "s1") {

    // s2 и init вызовутся в порядке написания кода
    private val s2 = run {
        println(s1)
        println("s2")
    }

    init {
        println("init")
        // Error. Init has access to everything that ABOVE ONLY
        // println(s3)
    }

    constructor(s3: String, s4: String) : this(s3) {
        // Second Constructor called after Primary + fields initializations + inits blocks
        // Which means, it has access to UNDER fields too
        println(s5)
        println("constructor")
    }

    private val s5 = {
        println("S5")
    }

}

fun main() {
    println("Primary:")
    InitSample("s1")
    println()
    println("Secondary:")
    InitSample("s1_second_constructor(s3)", "ss4")
}