package data_classes

data class DataClass(var param: String = "String", val a: A) {
    override fun equals(other: Any?): Boolean {
        return true
    }
//    Possible
//    override fun toString(): String {
//        return super.toString()
//    }

    override fun hashCode(): Int {
        return param.hashCode()
    }
}

class A

// impossible
// data class DataClassChild():DataClass()

fun main() {
    val d = DataClass("nice", A())
    d.param = "nice2"
    println(d)

    // copy
    val d2 = d.copy(param = "d2")

    // copy for collections
    val i = arrayListOf<Int>(1, 2, 3)
    i.clone()
}