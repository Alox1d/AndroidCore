package generics

open class Parent
class Child : Parent(), I1
interface I1
class InfoNew<T>(private val value: T) where T : Parent, T : I1 {

}

fun main() {
    // NO
//    val list:MutableList<Parent> = mutableListOf<Child>()
    val info = InfoNew<Child>(Child())
}


