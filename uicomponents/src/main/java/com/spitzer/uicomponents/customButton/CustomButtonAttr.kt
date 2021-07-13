package com.spitzer.uicomponents.customButton

import android.content.Context
import android.util.AttributeSet
import com.spitzer.uicomponents.R

internal data class CustomButtonAttr(
    val primaryColor: Int,
    val secondaryColor: Int,
    val isOutline: Boolean,
    val cornerRadius: Int,
    val strokeWidth: Int
)

internal object CustomButtonAttrParser {
    fun parse(context: Context, attr: AttributeSet?): CustomButtonAttr {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.CustomButton)

        val primaryColor = typedArray.getColor(
            R.styleable.CustomButton_primaryColor,
            CustomButton.DEFAULT_PRIMARY_COLOR
        )
        val secondaryColor =
            typedArray.getColor(
                R.styleable.CustomButton_secondaryColor,
                CustomButton.DEFAULT_SECONDARY_COLOR
            )
        val isOutline =
            typedArray.getBoolean(
                R.styleable.CustomButton_isOutline,
                CustomButton.IS_OUTLINE_DEFAULT_VALUE
            )
        val cornerRadius =
            typedArray.getInteger(
                R.styleable.CustomButton_isOutline,
                CustomButton.DEFAULT_CORNER_RADIUS
            )
        val strokeWidth =
            typedArray.getInteger(
                R.styleable.CustomButton_isOutline,
                CustomButton.DEFAULT_STROKE_WIDTH
            )

        typedArray.recycle()

        return CustomButtonAttr(
            primaryColor,
            secondaryColor,
            isOutline,
            cornerRadius,
            strokeWidth
        )
    }
}
