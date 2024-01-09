package collections

import java.util.Collections

fun main() {
    badMultithreading() //Неуправляемое поведение
    goodMultithreading() // Фикс синк-листом

    // Exceptions
//    badMultithreadingIterator()
//    badMultithreadingIteratorSync()

    goodMultithreadingIteratorSync() // Фикс synchronized(list)
}

fun badMultithreadingIterator() {
    val list = mutableListOf<Int>()
    for (i in 0..1000) {
        list.add(i)
    }
//    val syncList = Collections.synchronizedList(mutableListOf<Int>())
    val runnable1 = Runnable {
        val iterator = list.iterator()
        while (iterator.hasNext()) println(iterator.next())
    }
    val runnable2 = Runnable {
        list.remove(10)
    }

    val t1 = Thread(runnable1)
    val t2 = Thread(runnable2)
    t1.start()
    t2.start()
    t1.join()
    t2.join()
    println(list)
}

fun badMultithreadingIteratorSync() {
    val source = mutableListOf<Int>()
    for (i in 0..1000) {
        source.add(i)
    }
    val syncList = Collections.synchronizedList(source)
    val runnable1 = Runnable {
        val iterator = syncList.iterator()
        while (iterator.hasNext()) println(iterator.next())
    }
    val runnable2 = Runnable {
        syncList.remove(10)
    }

    val t1 = Thread(runnable1)
    val t2 = Thread(runnable2)
    t1.start()
    t2.start()
    t1.join()
    t2.join()
    println(syncList)
}

fun goodMultithreadingIteratorSync() {
    val source = mutableListOf<Int>()
    for (i in 0..1000) {
        source.add(i)
    }
    val syncList = Collections.synchronizedList(source)
    val runnable1 = Runnable {
        synchronized(syncList) {
            val iterator = syncList.iterator()
            while (iterator.hasNext()) println(iterator.next())
        }
    }
    val runnable2 = Runnable {
        syncList.remove(10)
    }

    val t1 = Thread(runnable1)
    val t2 = Thread(runnable2)
    t1.start()
    t2.start()
    t1.join()
    t2.join()
    println(syncList)
}

fun goodMultithreading() {
    val source = mutableListOf<Int>()
    for (i in 0..5) {
        source.add(i)
    }
    val syncList = Collections.synchronizedList(mutableListOf<Int>())
    val runnable = Runnable { syncList.addAll(source) }

    val t1 = Thread(runnable)
    val t2 = Thread(runnable)
    t1.start()
    t2.start()
    t1.join()
    t2.join()
    println(syncList)
}

fun badMultithreading() {
    val source = mutableListOf<Int>()
    for (i in 0..5) {
        source.add(i)

    }
    val target = mutableListOf<Int>()
    val runnable = Runnable {
        println(Thread.currentThread())
        // Потоки одновременно начали писать эл-ты в лист,
        // и в конце один из потоков может "перезаписать" результат добавления предыдущего
        target.addAll(source)
    }

    val t1 = Thread(runnable)
    val t2 = Thread(runnable)
    t1.start()
    t2.start()
    t1.join()
    t2.join()
    println(target)
}

