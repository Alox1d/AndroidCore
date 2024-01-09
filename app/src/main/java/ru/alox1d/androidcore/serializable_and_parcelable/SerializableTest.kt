package serializable

import java.lang.reflect.Field

fun main() {
    // All fields are private
    val person = Person(
        name = "John",
        surname = "Swimth",
        age = 21
    )

    val clazz: Class<Person> = person.javaClass
    val field: Field = clazz.getDeclaredField("name")

    field.isAccessible = true
    println(field.get(person))
    println(field.type)
    println(field.declaringClass)
}

class Person(
    private val name: String,
    private val surname: String,
    private val age: Int,
)