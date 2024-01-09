package collections

class Map {
}

fun main() {
    val map = hashMapOf<String, String>()
    // hashMap is actually better, because mapOf/mutableMapOf uses LinkedHashMap https://medium.com/swlh/kotlin-mutable-map-write-performance-90bd14bc67b0
//    val map = mutableMapOf<String, String>()
//    map.containsKey()
    println(map)

    for (entry in map.entries) {
        println(entry)
        println(entry.key)
    }
}
