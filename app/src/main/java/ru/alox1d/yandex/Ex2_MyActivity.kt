package ru.alox1d.yandex

/*
    Сделать отдельный экран, на котором будет отображаться кнопка,
    по нажатию на которую отображается картинка и показывается на экране
    Разработчик сам всё написал и отдал задачу на ревью
    Что сделать:
    Посмотреть, найти ошибки, исправить.
 */
//class Ex2_MyActivity1 : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val rootView = LinearLayout(context). apply {
//            orientation = LinearLayout.VERTICAL
//        }
//        setContentView(rootView)
//
//        val button = Button(context).apply {
//            layoutParams = LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                200
//            )
//        }
//        rootView.addView(button)
//
//        val imageView = ImageView(context).apply {
//            layoutParams = LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT,
//            )
//        }
//        rootView.addView(imageView)
//
//        button.setOnClickListener {
//            val downloads = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//            val files = downloads!!.listFiles()
//            files.forEach {
//                if ((it as File).name.endsWith(".jpg")){
//                    Thread {
//                        val image = BitmapFactory.decodeFile(it.path)
//                        imageView.setImageBitmap(image)
//                    }
//                    return@forEach
//                }
//
//                throw RuntimeException("There are no images in your downloads!!!!")
//            }
//        }
//    }
//}