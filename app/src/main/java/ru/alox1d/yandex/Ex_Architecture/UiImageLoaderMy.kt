//package ru.alox1d.yandex.Ex_Architecture
//
//abstract class UIImageLoader<L : Any>(
//    private val mapper: LinkMapper<L>,
//    private val loader: ImageLoader,
//    private val processor: ImageProcessor,
//) {
//    private val cache: ConcurrentHashMap<String, Map>
//
//    fun load(link: L, onImageResult: (Bitmap, Exception?) -> Unit) {
//        val rawLink = mapper.map(link)
//
//        val bitmap = if (cache[rawLink] != null) cache[rawLink] else
//            processor.runWork {
//                val bitmap = if (cache[rawLink] != null) cache[rawLink] else loader.load(rawLink)
//                if (bitmap != null) {
//                    cache.put(rawLink, bitmap)
//                    onImageResult(bitmap, null)
//                } else {
//                    onImageResult(bitmap, ImageLoadException("Failed to load image: $rawLink"))
//                }
//            }
//    }
//
//    class URI(mapper: LinkMapper<URI>, loader: ImageLoader, processor: ImageProcessor): UIImageLoader<URI>(mapper, loader, processor)
//    class RawString(mapper: LinkMapper<String>, loader: ImageLoader, processor: ImageProcessor): UIImageLoader<String>(mapper,loader, processor)
//}
//
//interface LinkMapper<T> {
//    fun map(link:T): String
//}
//
//interface ImageLoader {
//    fun load(link: String): Bitmap?
//}
//
//interface ImageProcessor {
//    fun runWork(work: () -> Unit): Cancellable
//}
//
//interface Cancellable {
//    fun cancel()
//}
//
//class SimpleImageProcessor : ImageProcessor {
//    override runWork(work: () -> Unit): Cancellable {
//        val thread = Thread {
//            work()
//        }
//        thread.start()
//
//        return object : Cancellable {
//            override fun cancel(){
//                thread.interrupt()
//            }
//        }
//    }
//}
//
//private handler = Hander(Looper.getMainLooper())
//...
//override fun onViewBind(item: Item){
//    val processor = SimpleImageProcessor()
//    val mapper = StringLinkMapper()
//    val loader = NetworkImageLoader()
//
//    UIImageLoader.RawString(processor, mapper, loader).load(item.url){ bitmap, exception ->
//        if (exception != null){
//            retryView.setVisible()
//        }
//        handler.post {
//            imageView.setBitmap(it)
//        }
//    }
//}
