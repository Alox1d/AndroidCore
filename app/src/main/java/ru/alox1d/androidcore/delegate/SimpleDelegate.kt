package delegate

interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() {
        print(x)
    }
}

class Derived(b: Base) : Base by b
//class Derived(b: Base) : Base by BaseImpl(10)

fun main() {
    val b = BaseImpl(10)
    Derived(b).print()
}