package generics

class Test1 {
}

fun main() {
    val parents: ArrayList<Parent1> = ArrayList()
    parents.add(Child1())
}

open class Parent1

class Child1() : Parent1()