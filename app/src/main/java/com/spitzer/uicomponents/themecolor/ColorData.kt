package com.spitzer.uicomponents.themecolor

import androidx.lifecycle.MutableLiveData

class ColorData {
    val redValue: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val greenValue: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val blueValue: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val hexColor: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val hexString: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    init {
        redValue.value = 0
        greenValue.value = 0
        blueValue.value = 0
    }
}