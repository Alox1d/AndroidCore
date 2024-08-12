package ru.alox1d.yandex.Ex2_MyActivity_Solution_Manager_ViewModel_Flow

import android.graphics.BitmapFactory
import android.os.Build
import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.alox1d.yandex.Ex2_PictureSuffixes
import java.io.File

class Ex2_ViewModel : ViewModel() {
    private val _imageFlow = MutableStateFlow<Ex2_State>(Ex2_State.Empty)
    val imageFlow = _imageFlow.asStateFlow()

    fun loadImageFromDownloads() {
        viewModelScope.launch(Dispatchers.IO) {
            val downloads = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                        .toString()
                )
            } else {
                File(Environment.getExternalStorageDirectory().toString() + "/Download/")
            }
            if (downloads.exists()) {
                val files: Array<File>? = downloads.listFiles()
                files?.forEach { file ->
                    Ex2_PictureSuffixes.entries.forEach { suffix ->
                        if (file.name.endsWith(suffix.value)) {
                            val image = BitmapFactory.decodeFile(file.path)
                            _imageFlow.emit(Ex2_State.Loaded(image))

                            return@launch
                        }
                    }
                }

                _imageFlow.emit(Ex2_State.Error)
            }
        }
    }
}