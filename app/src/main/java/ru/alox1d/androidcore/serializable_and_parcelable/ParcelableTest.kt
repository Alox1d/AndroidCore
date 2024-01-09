package parcelable

import android.os.Build
import android.os.Parcel
import android.os.Parcelable

object ParcelableTest {

    fun run(): Person? {
        // All fields are private
        val person = Person(
            name = "John",
            surname = "Swimth",
            age = 21
        )

        val source = Parcel.obtain()
        source.writeParcelable(person, 0)
        val bytes = source.marshall()
        source.recycle()

        val destination = Parcel.obtain()
        destination.unmarshall(bytes, 0, bytes.size)
        destination.setDataPosition(0)

        val classLoader = Person::class.java.classLoader
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val result = destination.readParcelable(classLoader, Person::class.java)
            println(result)
            return result
        }
        destination.recycle()
        return null
    }
}

data class Person(
    private val name: String,
    private val surname: String,
    private val age: Int,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeInt(age)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }
}