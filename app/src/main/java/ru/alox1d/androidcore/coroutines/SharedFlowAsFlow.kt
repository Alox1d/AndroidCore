package ru.alox1d.androidcore.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    main2()
}

fun main2() = runBlocking {
    // Создаём горячий SharedFlow
    val sharedFlow = MutableSharedFlow<Int>(replay = 1)

    // Присваиваем SharedFlow переменной типа Flow
    val hotFlowAsColdFlow: Flow<Int> = sharedFlow
    val coldFlow = flowOf(0, 1, 2, 3, 4, 5)

    // Запускаем корутину для эмуляции отправки данных
    val emitterJob = launch {
        repeat(5) {
            delay(100)
            sharedFlow.emit(it)
            println("Emit: $it")
        }
    }

    // Первому подписчику сразу начнут приходить данные
    val collector1Job = launch {
        delay(250)
        // в переменной типа Flow может быть горячий поток
        // в таком случае программа не завершит работу (ЕСЛИ флоу горячий)
        hotFlowAsColdFlow.collect {
            println("Collector 1 received: $it")
        }
    }

    // Второй подписчик подключается позже и также получает данные
//    val collector2Job = launch {
//        delay(500)  // Подключаем второго подписчика позже
//        hotFlowAsColdFlow.collect {
//            println("Collector 2 received: $it")
//        }
//    }

    // Ждём завершения всех корутин
//    emitterJob.join()
//    collector1Job.join()
//    collector2Job.join()
}