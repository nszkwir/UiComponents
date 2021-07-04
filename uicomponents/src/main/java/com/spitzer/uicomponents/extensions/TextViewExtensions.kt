package com.spitzer.uicomponents.extensions

import android.graphics.drawable.Drawable
import android.widget.TextView

fun TextView.setRightDrawable(errorDrawable: Drawable?) =
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(
        null,
        null,
        errorDrawable,
        null
    )