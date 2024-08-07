/*
Проблемы (спросить, зачем нужно то или иное улучшение):
1. Не используется java.util.concurrent (AtomicBoolean, ConcurrentLinkedQueue) - спросить, почему нужны;
2. Нет вызова start() при инициализации (в init);
3. Модификаторы доступа у свойств можно улучшить (достатогчно private)
4. (Минор) Метод post не возвращает this -> нет возможности работать с ним как с билдером
5.1 Лучше val task = taskQueue.get(0) -> val task = taskQueue[0]
5.2 А ещё лучше taskQueue.poll() (в кейсе 5.1 нужно удалить последний элемент)
5.2.1 В случае 5.2 понадобится null-check: например, task?.run()
6. (Бонус) Дополнить javadoc методов + написать для класса
7. (Бонус) Написать тест(-ы)
 */
package com.example.androidinterview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.locks.ReentrantLock

class SimpleHandler : Thread(TAG) {

    private val alive = AtomicBoolean(true)
    private val taskQueue = ConcurrentLinkedQueue<Runnable>()
    private val lock = ReentrantLock()
    private val condition = lock.newCondition()

    init {
        start()
    }

    /**
    This method posts the task into the thread
     */
    fun post(task: Runnable): SimpleHandler {
        taskQueue.add(task)
        condition.await()
        return this
    }

    /**
    Stops the handler
     */
    fun quit() {
        alive.set(false)
    }

    override fun run() {
        while (alive.get()) {
            val task: Runnable? = taskQueue.poll()
            if (task == null) {
                condition.signal()
            } else {
                task.run()
            }
        }
        Log.i(TAG, "SimpleHandler: Terminated")
    }

    companion object {
        private const val TAG = "SimpleHandler"
    }

    //tailrec имеет смысл ТОЛЬКО ЕСЛИ рекурсия идёт ПОСЛЕДНИМ ДЕЙСТВИЕМ
    tailrec fun <T : View> View.findViewById(childViewId: Int): T? {
        for (child in (this as? ViewGroup)?.children.orEmpty()) {
            child.findViewById<T>(childViewId)?.let { return it }
        }

        return (this as T?)?.takeIf { it.id == childViewId }
    }
}