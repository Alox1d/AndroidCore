package ru.alox1d.yandex.Ex2_MyActivity_Solution_Manager_ViewModel_Flow

import android.graphics.Bitmap

sealed class Ex2_State {
    data class Loaded(val image: Bitmap) : Ex2_State()
    data object Error : Ex2_State()
    data object Empty : Ex2_State()
}