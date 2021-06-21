package com.spitzer.uicomponents.extensions

import android.content.Context

fun Int.toPixels(context: Context) = (this * context.resources.displayMetrics.density).toInt()
