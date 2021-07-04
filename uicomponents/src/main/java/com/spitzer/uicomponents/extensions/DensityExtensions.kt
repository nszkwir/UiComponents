package com.spitzer.uicomponents.extensions

import android.content.Context
import android.content.res.Resources

fun Int.toPixels(context: Context) = (this * context.resources.displayMetrics.density).toInt()
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
