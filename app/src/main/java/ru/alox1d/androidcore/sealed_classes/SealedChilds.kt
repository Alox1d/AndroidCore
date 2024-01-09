package sealed_classes

sealed class IOError(string: String) : Error // extended only in same package and module
{

    init {
        println(string)
    }

    abstract fun foo()
}

open class CustomError : IOError("sealed_constructor") { // can be extended wherever it's visible
    override fun foo() {

    }
    // Impossible
    // abstract fun foo2()
}

fun main() {
    println()
    val ioError: IOError = CustomError()
    foo(ioError)
}

fun foo(ioError: IOError) {
    when (ioError) {
        is CustomError -> {
            println("Custom Error")
        }
    }
}

abstract class ABC {
    abstract class ABCD
}

