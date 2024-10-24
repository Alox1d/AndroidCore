package ru.alox1d.androidcore.coroutines.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Example for visualizing a new flow collect inside another flow collect.
 * When the outside one gets a new emit,
 * "Insided"-one will be cancelled and a new flow collect will be started.
 *
 * Print:
 * source started: 1
 * dest 10, source 1
 * dest 11, source 1
...
 * dest 18, source 1
 * source started: 2
 * dest 19, source 2
 * etc.
 */
suspend fun main() {
    val scope = CoroutineScope(Dispatchers.Default)

    val shared = MutableSharedFlow<Int>()
    val shared2 = MutableSharedFlow<Int>()

    scope.launch {
        shared2.collectLatest { source ->
            println("source started: $source")
            shared.collectLatest {
                println("dest $it, source $source")
            }
        }
    }
    scope.launch {
        for (i in 1..10) {
            delay(1000)
            shared2.emit(i)
        }
    }

    for (i in 1..500) {
        delay(100)
        shared.emit(i)
    }
}