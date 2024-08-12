package ru.alox1d.yandex.Ex2_MyActivity_Solution_SAF

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.locks.ReentrantLock


/*
    Сделать отдельный экран, на котором будет отображаться кнопка,
    по нажатию на которую отображается картинка и показывается на экране
    Разработчик сам всё написал и отдал задачу на ревью
    Что сделать:
    Посмотреть, найти ошибки, исправить.
 */
class Ex2_MyActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView

    // Регистрация Activity Result Launcher
    private val getImage = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        uri?.let {
            imageView.setImageURI(uri)
            imageView.visibility = View.VISIBLE
        } ?: run {
            Toast.makeText(this, "No image selected!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this

        val rootView = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }
        setContentView(rootView)

        val button = Button(
            context,
//            null,
//            com.google.android.material.R.style.Base_Widget_AppCompat_Button_Borderless_Colored
        ).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                200
            )
            text = "Show Image"
        }
        rootView.addView(button)

        imageView = ImageView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
            visibility = View.GONE
        }
        rootView.addView(imageView)

        button.setOnClickListener {
            // Запуск SAF для выбора изображения
            getImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.SingleMimeType("image/*")))
        }
        val r = ReentrantLock()
        r.lock()
    }

}