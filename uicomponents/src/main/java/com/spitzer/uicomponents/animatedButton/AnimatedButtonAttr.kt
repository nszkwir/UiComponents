package com.spitzer.uicomponents.animatedButton

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.spitzer.uicomponents.R
import com.spitzer.uicomponents.animatedButton.AnimatedButton.Companion.ANIMATION_DELAY

internal data class AnimatedButtonAttr(
    val buttonDefaultColor: Int,
    val buttonSuccessColor: Int,
    val buttonErrorColor: Int,
    val animationDelay: Int
)

internal object AnimatedButtonAttrParser {
    @SuppressLint("Recycle")
    fun parse(context: Context, attr: AttributeSet?): AnimatedButtonAttr {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AnimatedButton)

        val buttonDefaultColor = typedArray.getInteger(
            R.styleable.AnimatedButton_buttonDefaultColor,
            Color().toArgb()
        )
        val buttonSuccessColor =
            typedArray.getInteger(
                R.styleable.AnimatedButton_buttonSuccessColor,
                Color().toArgb()
            )
        val buttonErrorColor =
            typedArray.getInteger(
                R.styleable.AnimatedButton_buttonErrorColor,
                Color().toArgb()
            )
        val animationDelay =
            typedArray.getInteger(
                R.styleable.AnimatedButton_animationDelay,
                ANIMATION_DELAY
            )

        return AnimatedButtonAttr(
            buttonDefaultColor,
            buttonSuccessColor,
            buttonErrorColor,
            animationDelay
        )
    }
}