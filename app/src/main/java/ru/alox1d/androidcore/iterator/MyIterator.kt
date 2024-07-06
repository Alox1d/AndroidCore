package ru.alox1d.androidcore.iterator

import MyDate
import followingDate

class DateRange(
    val start: MyDate,
    val end: MyDate
) : Iterable<MyDate> {

    private var current = start

    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            override fun hasNext(): Boolean {
                return current <= end
            }

            override fun next(): MyDate {
                val old = current
                current = current.followingDate()
                return old
            }
        }
    }

}

fun iterateOverDateRange(
    firstDate: MyDate,
    secondDate: MyDate,
    handler: (MyDate) -> Unit
) {
    for (date in firstDate..secondDate) {
        handler(date)
    }
}

operator fun MyDate.rangeTo(other: MyDate) = DateRange(
    this,
    other
)
