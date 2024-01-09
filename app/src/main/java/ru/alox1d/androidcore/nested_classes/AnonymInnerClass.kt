package nested_classes

import java.util.concurrent.Executors

interface Math {
    fun doOperation(): Int
    fun doOperation2(): Int
}

// Single Abstract Method (SAM) interface Example
fun interface SimpleInterface {
    fun doOperation()
}

fun main() {

    // Example with object
    val math: Math = object : Math {
        override fun doOperation(): Int {
            println("Example 1 - object")
            return 1 + 1
        }

        override fun doOperation2(): Int {
            return 1 + 2
        }
    }
    println(math.doOperation())

    // Example 2 - SAM calling from Java
    val threadPool = Executors.newSingleThreadExecutor()
    // Java signature: void execute(Runnable command)
    threadPool.execute { println("Example 3 - SAM calling from Java") }

    // Example 3 - SAM calling (свой класс)
    foo {
        println("Example 2 - SAM calling")
    }
}

fun foo(simpleInterface: SimpleInterface) {
    simpleInterface.doOperation()
}


