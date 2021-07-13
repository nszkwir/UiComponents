package com.spitzer.uicomponents.animatedButton

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.spitzer.uicomponents.R
import com.spitzer.uicomponents.animatedButton.AnimatedButton.Companion.ANIMATION_DELAY
import com.spitzer.uicomponents.animatedButton.AnimatedButton.Companion.DEFAULT_BUTTON_COLOR
import com.spitzer.uicomponents.animatedButton.AnimatedButton.Companion.DEFAULT_BUTTON_TEXT
import com.spitzer.uicomponents.animatedButton.AnimatedButton.Companion.ERROR_BUTTON_COLOR
import com.spitzer.uicomponents.animatedButton.AnimatedButton.Companion.PROGRESS_COLOR
import com.spitzer.uicomponents.animatedButton.AnimatedButton.Companion.SUCCESS_BUTTON_COLOR
import com.spitzer.uicomponents.animatedButton.AnimatedButton.Companion.TEXT_COLOR

internal data class AnimatedButtonAttr(
    val buttonDefaultColor: Int,
    val buttonSuccessColor: Int,
    val buttonErrorColor: Int,
    val animationDelay: Int,
    val progressColor: Int,
    val buttonText: String,
    val textColor: Int
)

internal object AnimatedButtonAttrParser {
    fun parse(context: Context, attr: AttributeSet?): AnimatedButtonAttr {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AnimatedButton)

        val buttonDefaultColor = typedArray.getInteger(
            R.styleable.AnimatedButton_buttonDefaultColor,
            Color.valueOf(DEFAULT_BUTTON_COLOR).toArgb()
        )
        val buttonSuccessColor = typedArray.getInteger(
            R.styleable.AnimatedButton_buttonSuccessColor,
            Color.valueOf(SUCCESS_BUTTON_COLOR).toArgb()
        )
        val buttonErrorColor = typedArray.getInteger(
            R.styleable.AnimatedButton_buttonErrorColor,
            Color.valueOf(ERROR_BUTTON_COLOR).toArgb()
        )
        val animationDelay = typedArray.getInteger(
            R.styleable.AnimatedButton_animationDelay,
            ANIMATION_DELAY
        )
        val progressColor = typedArray.getInteger(
            R.styleable.AnimatedButton_progressColor,
            Color.valueOf(PROGRESS_COLOR).toArgb()
        )
        var buttonText = typedArray.getString(
            R.styleable.AnimatedButton_buttonText
        )
        if (buttonText == null) buttonText = DEFAULT_BUTTON_TEXT
        val textColor = typedArray.getInteger(
            R.styleable.AnimatedButton_textColor,
            Color.valueOf(TEXT_COLOR).toArgb()
        )

        typedArray.recycle()

        return AnimatedButtonAttr(
            buttonDefaultColor,
            buttonSuccessColor,
            buttonErrorColor,
            animationDelay,
            progressColor,
            buttonText,
            textColor
        )
    }
}
