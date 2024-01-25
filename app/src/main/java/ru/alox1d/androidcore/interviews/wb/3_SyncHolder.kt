package ru.alox1d.androidcore.interviews.wb

import java.util.concurrent.CopyOnWriteArrayList
import kotlin.concurrent.thread

fun main() {
    val holder = SyncHolderWithLock()
    thread {
        for (i in 0..1000) {
            holder.add(holder.getHolder().size.toString())
        }
    }
    thread {
        for (i in 0..1000) {
            holder.add(holder.getHolder().size.toString())
        }
    }
    thread {
        Thread.sleep(500)
        for (i in holder.getHolder()) {
            println(i)
        }
    }
}

class SyncHolderCopyOnWrite {

    private val holder: MutableList<String> = CopyOnWriteArrayList()

    fun getHolder(): List<String> {
        return holder.toList()
    }

    private fun log() {
        println("Incremented. Size = ${holder.size}")
        println(Thread.currentThread().name)
    }

    fun add() {
        // что делает этот метод?
        holder.add(holder.size.toString())
        log()

    }
}

class SyncHolderWithLock {

    private var holder: List<String> = listOf()
    private val lock = Any()

    fun getHolder(): List<String> {
        return ImmutableList(holder)
    }

    private fun log() {
        println("Incremented. Size = ${holder.size}")
        println(Thread.currentThread().name)
    }

    fun add(str: String) = synchronized(lock) {
        // что делает этот метод?
        holder = holder + str
        log()
    }

    // ImmutableList и MutableList будут в параллельных иерархиях, поэтому каст не сработает
    private class ImmutableList<T>(private val protectedList: List<T>) : List<T> by protectedList
}

