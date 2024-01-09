package inheritance.kt

open class B {
    open val s1 = "s1"
    fun foo() {

    }
}

class A : B() {
    override val s1 = "s2"

    init {
        s1
    }

    fun foo(s2: String) {

    }
}

fun main() {
    val b = B()
    val a = A()
    a.foo() // A has base method foo() too
    a.foo("Overloaded")
}