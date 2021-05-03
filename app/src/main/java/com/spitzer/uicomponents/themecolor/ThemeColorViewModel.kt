package com.spitzer.uicomponents.themecolor

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class ThemeColorViewModel : ViewModel() {
    val redValue: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val greenValue: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val blueValue: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val hexColor: MutableLiveData<Int> by lazy {MutableLiveData<Int>()}
    val hexString: MutableLiveData<String> by lazy {MutableLiveData<String>()}

    val red : LiveData<Int>
    get() = redValue

    val green : LiveData<Int>
        get() = greenValue

    val blue : LiveData<Int>
        get() = blueValue

    fun calculateHexColor() : String = Integer.toHexString(
        Color.rgb(
            redValue.value ?: 0,
            greenValue.value ?: 0,
            blueValue.value ?: 0
        )
    ).substring(2)

    fun updateColor(): Int {
        hexString.value = "#" + calculateHexColor()
        hexColor.value = Color.parseColor(hexString.value)
        return hexColor.value!!
    }

    init {
        redValue.value = 0
        greenValue.value = 0
        blueValue.value = 0


    }
}