package ru.alox1d.androidcore.polymorphism

open class A {
    open fun foo() {
        println("A")
    }
}

class B : A() {
    override fun foo() {
        println("B")
    }
}

fun main() {
    val variable: A = B()
    variable.foo()
}