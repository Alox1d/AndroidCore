package ru.alox1d.yandex.Ex2_MyActivity_Solution_MANAGE_EXTERNAL_STORAGE

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.alox1d.yandex.Ex2_PictureSuffixes
import java.io.File


/*
    Сделать отдельный экран, на котором будет отображаться кнопка,
    по нажатию на которую отображается картинка и показывается на экране
    Разработчик сам всё написал и отдал задачу на ревью
    Что сделать:
    Посмотреть, найти ошибки, исправить.
 */
class Ex2_MyActivity : AppCompatActivity() {

    private val STORAGE_PERMISSION_CODE: Int = 23
    private lateinit var imageView: ImageView

    private val storageActivityResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                    //Android is 11 (R) or above
                    if (Environment.isExternalStorageManager()) {
                        readImages()

                        Toast.makeText(
                            this,
                            "Manage External Storage Permissions Granted",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d(
                            "Ex2_MyActivity",
                            "onActivityResult: Manage External Storage Permissions Granted"
                        )
                    } else {
                        Toast.makeText(
                            this,
                            "Storage Permissions Denied",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                else -> {
                    //Below android 11
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this

        val rootView = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }
        setContentView(rootView)

        val button = Button(context).apply {
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
            if (checkStoragePermissions()) {
                readImages();

            } else {
                requestForStoragePermissions()
                readImages();
            }
        }
    }

    private fun checkStoragePermissions(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //Android is 11 (R) or above
            return Environment.isExternalStorageManager()
        } else {
            //Below android 11
            val read =
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)

            return read == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestForStoragePermissions() {
        //Android is 11 (R) or above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            try {
                val intent = Intent()
                intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                val uri = Uri.fromParts("package", this.packageName, null)
                intent.setData(uri)
                storageActivityResultLauncher.launch(intent)
            } catch (e: java.lang.Exception) {
                val intent = Intent()
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                storageActivityResultLauncher.launch(intent)
            }
        } else {
            //Below android 11
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                STORAGE_PERMISSION_CODE
            )
        }
    }

    private fun readImages() {
        val downloads = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //val downloads = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    .toString()
            )
        } else {
            File(Environment.getExternalStorageDirectory().toString() + "/Download/")
        }
        if (downloads.exists()) {
            val files: Array<File?>? = downloads.listFiles()
            files?.forEach { file ->
                Ex2_PictureSuffixes.entries.forEach { suffix ->
                    if (file?.name?.endsWith(suffix.value) == true) {
                        lifecycleScope.launch(Dispatchers.IO) {
                            val image = BitmapFactory.decodeFile(file.path)
                            lifecycleScope.launch {
                                imageView.setImageBitmap(image)
                                imageView.visibility = View.VISIBLE // Show the ImageView
                            }
                        }

                        return
                    }
                }
            }

        }

        Toast.makeText(this, "There are no images in your downloads!!!!", Toast.LENGTH_SHORT).show()
    }
}