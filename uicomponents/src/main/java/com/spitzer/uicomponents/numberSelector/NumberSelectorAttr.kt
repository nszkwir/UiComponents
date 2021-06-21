package com.spitzer.uicomponents.numberSelector

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.spitzer.uicomponents.R

internal data class NumberSelectorAttr(
    val minNumber: Int,
    val maxNumber: Int,
    val selectedNumber: Int,
    val delay: Int,
    val iconColor: Int
)

internal object NumberSelectorAttrParser {
    @SuppressLint("Recycle")
    fun parse(context: Context, attr: AttributeSet?): NumberSelectorAttr {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.NumberSelector)

        val minNumber = typedArray.getInteger(
            R.styleable.NumberSelector_minNumber,
            NumberSelector.DEFAULT_MIN_NUMBER
        )
        val maxNumber =
            typedArray.getInteger(
                R.styleable.NumberSelector_maxNumber,
                NumberSelector.DEFAULT_MAX_NUMBER
            )
        val selectedNumber =
            typedArray.getInteger(
                R.styleable.NumberSelector_selectedNumber,
                NumberSelector.DEFAULT_SELECTED_NUMBER
            )
        val delay =
            typedArray.getInteger(
                R.styleable.NumberSelector_delay,
                NumberSelector.DEFAULT_DELAY
            )
        val iconColor =
            typedArray.getInteger(
                R.styleable.NumberSelector_numberSelectorIconColor,
                Color().toArgb()
            )

        return NumberSelectorAttr(minNumber, maxNumber, selectedNumber, delay, iconColor)
    }
}
