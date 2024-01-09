package ru.alox1d.androidcore

import android.os.Build
import android.os.Parcel
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import parcelable.ParcelableTest
import parcelable.Person

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CoreInstrumentedTest {
    @Test
    fun parcelableTest() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
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
            assertEquals(person, ParcelableTest.run())
        }
        destination.recycle()
    }
}
