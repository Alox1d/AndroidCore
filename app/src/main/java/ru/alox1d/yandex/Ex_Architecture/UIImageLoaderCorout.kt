import android.graphics.Bitmap
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.ref.WeakReference

// Исключение для ошибки загрузки
class ImageLoadExceptionC(message: String) : Exception(message)

// Простой маппер остаётся публичным
interface LinkMapperC<T> {
    fun map(link: T): String
}

// Интерфейс для отмены задач
interface CancellableC {
    fun cancel()
}

// Основной класс UIImageLoader теперь использует корутины
abstract class UIImageLoaderC<L : Any>(
    private val worker: WorkerC,
    private val mapper: LinkMapperC<L>,
    private val loader: DataImageLoaderC
) {

    // Заменяем коллбек на suspend-функцию
    suspend fun load(target: Any, link: L): Bitmap {
        val rawLink = mapper.map(link)

        // Асинхронная работа через корутину
        return worker.runWork<Bitmap>(WeakReference(target)) {
            val bitmap = loader.load(rawLink)
                ?: throw ImageLoadException("Failed to load image from $rawLink")
            bitmap
        }
    }

    // Конкретные реализации для URI и строк
    class URI(worker: WorkerC, loader: DataImageLoaderC, mapper: LinkMapperC<URI>) :
        UIImageLoaderC<URI>(worker, mapper, loader)

    class Raw(worker: WorkerC, loader: DataImageLoaderC, mapper: LinkMapperC<String>) :
        UIImageLoaderC<String>(worker, mapper, loader)
}

// Worker теперь использует корутины для выполнения работы
interface WorkerC {
    suspend fun <T> runWork(targetRef: WeakReference<Any>, work: suspend () -> T): T
}

// Интерфейс для загрузки данных
interface DataImageLoaderC {
    fun load(rawLink: String): Bitmap?
}

// Пример реализации Worker с корутинами
internal class CoroutineWorkerC : WorkerC {
    override suspend fun <T> runWork(targetRef: WeakReference<Any>, work: suspend () -> T): T {
        return withContext(Dispatchers.IO) {
            if (targetRef.get() != null) {
                work() // Выполняем работу в IO-потоке
            } else {
                throw CancellationException("Target was garbage collected")
            }
        }
    }
}

//import android.graphics.Bitmap
//import java.lang.ref.WeakReference
//
//interface LinkMapper<T> {
//    fun map(link: T): String
//}
//
//interface Cancellable {
//    fun cancel()
//}
//
//interface UIImageLoader<L : Any> {
//    // Теперь коллбэк возвращает Bitmap или бросает исключение
//    fun load(target: Any, link: L, callback: (Bitmap) -> Unit): Cancellable
//
//    abstract class Base<L : Any>(
//        private val processor: ImageProcessor = ImageProcessorImpl(),
//        private val mapper: LinkMapper<L>,
//        private val loader: DataImageLoader
//    ) : UIImageLoader<L> {
//
//        override fun load(target: Any, link: L, callback: (Bitmap) -> Unit): Cancellable {
//            val rawLink = mapper.map(link)
//
//            // Запускаем задачу в worker с передачей слабой ссылки на target
//            return processor.runWork(WeakReference(target)) {
//                // Загружаем изображение, и если оно не удалось — бросаем исключение
//                val bitmap = loader.load(rawLink)
//                    ?: throw ImageLoadException("Failed to load image from $rawLink")
//
//                callback(bitmap) // Передаем результат
//            }
//        }
//    }
//
//    class URI(processor: ImageProcessor = ImageProcessorImpl(), loader: DataImageLoader, mapper: LinkMapper<URI>) : Base<URI>(processor, mapper, loader)
//    class Raw(processor: ImageProcessor = ImageProcessorImpl(), loader: DataImageLoader, mapper: LinkMapper<String>) : Base<String>(processor, mapper, loader)
//}
//
//// Интерфейс для загрузки данных, возвращает Bitmap или null, если не удалось загрузить
//internal interface DataImageLoader {
//    fun load(rawLink: String): Bitmap?
//}
//
//// Другими реализациями интерфейса, помимо DefaultProcessor,
//// могут быть реализации для корутин и RxJava
//interface ImageProcessor {
//    // Выполнение работы, которая может бросить исключение
//    fun runWork(targetRef: WeakReference<Any>, work: () -> Unit): Cancellable
//}
//
//class ImageProcessorImpl : ImageProcessor {
//    override fun runWork(targetRef: WeakReference<Any>, work: () -> Unit): Cancellable {
//        val thread = Thread {
//            if (targetRef.get() != null) {
////                try {
//                    work() // Выполняем работу
////                } catch (e: Exception) {
////                    // Здесь можно обработать исключение, если нужно
////                    println("Error: ${e.message}")
////                }
//            }
//        }
//        thread.start()
//
//        return object : Cancellable {
//            override fun cancel() {
//                thread.interrupt() // Прерываем поток, если требуется отмена
//            }
//        }
//    }
//}
//// Исключение, которое будет брошено при неудачной загрузке
//class ImageLoadException(message: String) : Exception(message)
//
