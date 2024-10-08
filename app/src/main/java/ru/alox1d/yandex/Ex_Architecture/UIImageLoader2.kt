import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.lang.ref.WeakReference

// Исключение должно быть доступно извне, чтобы пользователи могли его ловить
class ImageLoadException(message: String) : Exception(message)

// Простой маппер может быть использован в разных частях, так что его оставляем публичным
interface LinkMapper<T> {
    fun map(link: T): String
}

// Делаем интерфейс internal, так как он относится к внутреннему механизму отмены задач
interface Cancellable {
    fun cancel()
}

// Сам UIImageLoader может быть виден снаружи, так что его не трогаем
abstract class UIImageLoader<L : Any>(
    private val processor: ImageProcessor = ImageProcessorImpl(),
    private val mapper: LinkMapper<L>, // можно повыше
    private val loader: DataImageLoader = NetworkImageLoader()
) {

    // Метод загрузки изображения
    fun load(target: Any, link: L, onImageResult: (Bitmap) -> Unit): Cancellable {
        val rawLink = mapper.map(link)

        return processor.runWork(WeakReference(target)) {
            val bitmap = loader.load(rawLink)
                ?: throw ImageLoadException("Failed to load image from $rawLink")
            onImageResult(bitmap)
        }
    }

    // Эти классы можно оставить видимыми, так как их могут использовать для конкретных типов ссылок
    class URI(worker: ImageProcessor, loader: DataImageLoader, mapper: LinkMapper<URI>) :
        UIImageLoader<URI>(worker, mapper, loader)

    class FilePath(
        worker: ImageProcessor,
        loader: DataImageLoader,
        mapper: LinkMapper<java.io.File>
    ) :
        UIImageLoader<java.io.File>(worker, mapper, loader)

    class RawString(worker: ImageProcessor, loader: DataImageLoader, mapper: LinkMapper<String>) :
        UIImageLoader<String>(worker, mapper, loader)
}

// Worker используется только для внутренней работы с потоками, так что делаем его internal
interface ImageProcessor {
    fun runWork(targetRef: WeakReference<Any>, work: () -> Unit): Cancellable
}

// DataImageLoader - это компонент, который может быть публичным, так как он отвечает за загрузку изображений
interface DataImageLoader {
    fun load(rawLink: String): Bitmap?
}


// РЕАЛИЗАЦИИ
// Простой LinkMapper для строковых ссылок
class StringLinkMapper : LinkMapper<String> {
    override fun map(link: String): String {
        return link // В случае строки возвращаем ее напрямую
    }
}

class ImageProcessorImpl : ImageProcessor {
    override fun runWork(targetRef: WeakReference<Any>, work: () -> Unit): Cancellable {
        val thread = Thread {
            if (targetRef.get() != null) {
//                try {
                work() // Выполняем работу
//                } catch (e: Exception) {
//                    // Здесь можно обработать исключение, если нужно
//                    println("Error: ${e.message}")
//                }
            }
        }
        thread.start()

        return object : Cancellable {
            override fun cancel() {
                thread.interrupt() // Прерываем поток, если требуется отмена
            }
        }
    }
}

// Реализация DataImageLoader, которая использует стандартный метод загрузки Bitmap через URL
class NetworkImageLoader : DataImageLoader {
    override fun load(rawLink: String): Bitmap? {
        // Простая реализация загрузки изображения через URL
        return try {
            val url = java.net.URL(rawLink)
            val connection = url.openConnection()
            connection.connect()
            val inputStream = connection.getInputStream()
            BitmapFactory.decodeStream(inputStream) // Возвращаем Bitmap
        } catch (e: Exception) {
            null // Если что-то пошло не так, возвращаем null
        }
    }
}


// ИНИЦИАЛИЗАЦИЯ
// Пример инициализации загрузчика для строковых ссылок
fun initializeLoader(): UIImageLoader<String> {
    val worker = ImageProcessorImpl() // Создаем worker
    val mapper = StringLinkMapper() // Создаем маппер для строк
    val loader = NetworkImageLoader() // Создаем загрузчик изображений

    // Создаем экземпляр UIImageLoader.Raw, который использует строковые ссылки
    return UIImageLoader.RawString(worker, loader, mapper)
}