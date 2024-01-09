package nested_classes

class InnerClass {
    private val outer = 1

    init {
        val inner = Inner()
        // val a = 1 + inner.va NO ACCESS
    }

    inner class Inner {
        private val inner = 2
        fun print() {
            println(outer)
            println(inner)

        }

    }

}

fun main() {
    InnerClass().Inner().print()
}