package collections

import java.util.PriorityQueue

fun main() {
    // Queue
    val queue = PriorityQueue<Student>()
    val s0 = Student(name = "Zasha")
    val s1 = Student(name = "Sasha")
    val s2 = Student(name = "Asha")
    val s3 = Student(name = "BAsha")
    val s4 = Student(name = "Masha")
    queue.add(s0)
    queue.add(s1)
    queue.add(s2)
    queue.add(s3)
    queue.add(s4)

    println(queue)
    println(queue.remove())
    println(queue.remove())
    println(queue.remove())
    println(queue.remove())
    println(queue.remove())
    println()

    // Deque
    val a = ArrayDeque<Student>()
    a.add(s1)
    println(a[0])
}

data class Student(val name: String) : Comparable<Student> {
    override fun compareTo(other: Student): Int {
        return name.compareTo(other.name)
    }

}