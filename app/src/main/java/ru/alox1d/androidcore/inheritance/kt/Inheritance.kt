package inheritance.kt

open class B {
    init {
        println("init b")
    }

    open val s1 = "s1"
    open fun foo() {
        println("B")
    }
}

class A : B() {

    init {
        println("init a")
    }

    override val s1 = "s2"

    init {
        s1
    }

    fun foo(s2: String) {
        println(s2)
    }

    override fun foo() {
        println("A")
    }
}

fun main() {
    val b = B()
    val a = A()
    a.foo() // A has base method foo() too
    a.foo("Overloaded")

    val var_A_as_Parent_B: B = A()
    var_A_as_Parent_B.foo()
}