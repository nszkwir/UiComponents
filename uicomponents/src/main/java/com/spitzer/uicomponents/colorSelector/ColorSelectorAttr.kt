package com.spitzer.uicomponents.colorSelector

import android.content.Context
import android.util.AttributeSet
import com.spitzer.uicomponents.R
import com.spitzer.uicomponents.colorSelector.ColorSelector.Companion.MAX_PROGRESS_VALUE
import com.spitzer.uicomponents.colorSelector.ColorSelector.Companion.MIN_PROGRESS_VALUE
import com.spitzer.uicomponents.colorSelector.ColorSelector.Companion.STARTING_PROGRESS_VALUE

internal data class ColorSelectorAttr(
    val startingProgress: Int,
    val minProgress: Int,
    val maxProgress: Int
)

internal object ColorSelectorAttrParser {
    fun parse(context: Context, attr: AttributeSet?): ColorSelectorAttr {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.ColorSelector)

        val startingProgress = typedArray.getInteger(
            R.styleable.ColorSelector_startingProgress,
            STARTING_PROGRESS_VALUE
        )
        val minProgress =
            typedArray.getInteger(R.styleable.ColorSelector_minProgress, MIN_PROGRESS_VALUE)
        val maxProgress =
            typedArray.getInteger(R.styleable.ColorSelector_maxProgress, MAX_PROGRESS_VALUE)

        return ColorSelectorAttr(startingProgress, minProgress, maxProgress)
    }
}