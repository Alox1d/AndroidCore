package ru.alox1d.androidcore.inline

fun main() {
    inlined { count ->
        println("do something here $count")
    }
}

inline fun inlined(block: (Int) -> Unit) {
    var count = 0
    println("before")
    block(++count)
    println("after")
}