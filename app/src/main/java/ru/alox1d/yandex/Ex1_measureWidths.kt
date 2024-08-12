package ru.alox1d.yandex

//Дано:
//parentWidth - ширина родительского контейнера
//childSpecs - спецификации ширин дочерних View контейнера, целые числа, причём:
//1. Если childSpecs[i] >= 0, то дочерня View имеет фиксированную ширину, роавную childSpecs[i]
//2. Если childSpecs[i] < 0, то это доля, которую займет View от неиспользованной ширины parentWidth
// после расположения в нём всех View с фиксированной шириной.
//Требуется определить финальные размеры всех View
//Пример: measureWidths(100, listOf(50, -3, -2) == listOf(50, 30, 20)

fun measureWidths(parentWidth: Int, childSpecs: List<Int>): List<Int> {
    val result = childSpecs.toMutableList()
    val partsOfViewMap = hashMapOf<Int, Int>()

    var availableWidth = parentWidth
    var denominator = 0

    childSpecs.forEachIndexed { childWidthIndex, childWidth ->
        if (childWidth >= 0) {
            val delta = availableWidth - childWidth
            if (delta < 0) {
                result[childWidthIndex] = availableWidth
            } else {
                result[childWidthIndex] = childWidth
            }
            availableWidth -= childWidth
        } else {
            val absChild = Math.abs(childWidth)
            denominator += absChild
            partsOfViewMap[childWidthIndex] = absChild
        }
    }
    val onePartOfView = availableWidth / denominator
    partsOfViewMap.entries.forEach { child ->
        result[child.key] = onePartOfView * child.value // можно обойтись без хеш-мапы, сделав повторный for-each
    }
    return result
}

fun main() {
    println(measureWidths(100, listOf(50, -3, -2)))
}