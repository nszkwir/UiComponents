package com.spitzer.uicomponents.extensions

import android.graphics.drawable.Drawable
import android.widget.EditText

fun EditText.setRightDrawable(errorDrawable: Drawable?) =
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(
        null,
        null,
        errorDrawable,
        null
    )