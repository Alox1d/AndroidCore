package data_classes

data class Doggo(
    val name: String,
    val breed: String,
    val rating: Int = 11
)


fun main() {
    val goodDoggo = Doggo("Раф", "Раф", 1)

    val (name, rating) = goodDoggo

    println(name)
}