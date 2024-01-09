package delegate

import kotlin.reflect.KProperty


class MyLazy(
    private val initialize: () -> Any
) {
    private var value: Any? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Any {
        return if (value == null) {
            value = initialize()
            value!!
        } else value!!
    }
}

private val obj by MyLazy {
    println("Hello world")
    3
}

fun main() {
    println(obj)
}