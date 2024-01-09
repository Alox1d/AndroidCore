package nested_classes

class LocalInnerClass {
    private val a = 10
    fun foo() {
        //Modifiers don't work: private / protected
        class LocalMethod() {
            val b = a
        }

        val localMethod = LocalMethod()
    }
}

fun main() {
    // No access to local method classes
    // LocalMethod()
}