package nothing

// Will NOT compile with Nothing:
// is SomeError.UnitError -> println() // need to return exception
// Use if function will NEVER return value - like if you must throw exception

// Will be OK with Unit
// fun getData(onError: (SomeError) -> Unit): Data  {

fun getData(onError: (SomeError) -> Nothing): Data {
    onError.invoke(SomeError.InvalidStatement("Беда"))
    // some logic
    // ..
    return Data()
}

fun foo(): Data {
    val data = getData() { err ->
        when (err) {
            is SomeError.InvalidStatement -> throw Exception(err.parseError)
            is SomeError.NoSuchData -> TODO()
//            is SomeError.UnitError -> println() // need to return exception
        }
    }
    return data //успешный сценарий
}

fun main() {
    println(foo())
}

sealed class SomeError(parseError: String) {
    data class InvalidStatement(val parseError: String) : SomeError(parseError)
    data class NoSuchData(val parseError: String) : SomeError(parseError)
//    data class UnitError(val parseError: String) : SomeError(parseError)
}

class Data

