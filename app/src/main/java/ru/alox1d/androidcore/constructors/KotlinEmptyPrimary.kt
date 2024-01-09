package constructors

class KotlinEmptyPrimary {
    constructor(s: String) {
        println(s)
    }
}

fun main() {
    // No Default Constructor exist because of Second Constructor
    // KotlinEmptyPrimary()
    KotlinEmptyPrimary("Hi")
}