package nothing

//Скомпилируются нормально
fun funOne(): Unit {
    while (true) {
    }
}

fun funTwo(): Nothing {
    while (true) {
    }
}


//Ок
fun funThree(): Unit {
    println("hi")
}
//Не ок
//fun funFour(): Nothing { println("hi") }