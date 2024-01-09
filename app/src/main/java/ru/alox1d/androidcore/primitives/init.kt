package primitives

class Init() {
//    val a = foo(b) - Wrong init order


    private val b = 0
    private val a = foo(b) // Right init order

    private fun foo(b: Int): Any {
        return Any()
    }
}