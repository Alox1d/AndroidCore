//package ru.alox1d.yandex.Ex_Architecture
//
//import DataImageLoader
//
//interface LinkMapper<T> {
//    fun map(link: T): String
//}
//
//interface Lifecycle {
//    fun subscribeOnDie(onDied: () -> Unit)
//}
//
//interface Cancaellable {
//    fun cancel()
//    fun waitResult(): ImageState
//}
//
//interface UIImageLoader<L : Any> {
//
//    fun load(lifecycle: Lifecycle, link: L): ImageState
//
//    abstract class Base<L : Any>(
//        private val worker: Worker,
//        private val mapper: LinkMapper<L>,
//        private val loader: DataImageLoader,
//    ) : UIImageLoader<L> {
//
//        override fun load(lifecycle: Lifecycle, link: L): ImageState {
//            val rawLink = mapper.map(link)
//
//            val cancelWork = worker.runWork {
//                loader.load(rawLink)
//            }
//
//            lifecycle.subscribeOnDie {
//                cancelWork.cancel()
//            }
//
//            return cancelWork.waitResult()
//        }
//    }
//
//    class URI(val worker: Worker, loader: DataImageLoader, val mapper: LinkMapper<URI>) : Base<URI>(worker, mapper, loader)
//    class Raw(val worker: Worker, loader: DataImageLoader, val mapper: LinkMapper<String>) : Base<String>(worker, mapper, loader)
//}
//
//interface Worker {
//    fun runWork(work: () -> ImageState): Cancellable
//}
//
//interface ImageState {
//    class Bitmap(private val bitmap: android.graphics.Bitmap) : ImageState
//    class Empty() : ImageState
//}
//
//interface DataImageLoaderOld {
//    fun load(rawLink: String): ImageState
//}
//
