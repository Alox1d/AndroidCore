package ru.alox1d.androidcore.interviews.wb

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val coroutineContext = Job() + Dispatchers.Default
    val coroutinesScope = CoroutineScope(coroutineContext)
    val job = coroutinesScope.launch {
        val request = launch {
            GlobalScope.launch {
                delay(1000)
                println("1")
            }

            launch {
                delay(100)
                println("2")
                delay(1000)
                println("3")
            }
        }
        delay(500)
        request.cancel()
        delay(1000)
        println("4")
    }
    runBlocking {
        job.join()
    }
}