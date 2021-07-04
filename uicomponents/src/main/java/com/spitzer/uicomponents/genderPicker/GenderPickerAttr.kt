package com.spitzer.uicomponents.genderPicker

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.spitzer.uicomponents.R

internal data class GenderPickerAttr(
    val undefinedAvailable: Boolean,
    val iconColor: Int,
    val backgroundColor : Int,
    val defaultSelectedChoice: Int
)

internal object GenderPickerAttrParser {
    @SuppressLint("Recycle")
    fun parse(context: Context, attr: AttributeSet?): GenderPickerAttr {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.GenderPicker)

        val undefinedAvailable = typedArray.getBoolean(
            R.styleable.GenderPicker_undefinedAvailable,
            GenderPicker.DEFAULT_UNDEFINED_AVAILABLE
        )
        val iconColor =
            typedArray.getInteger(
                R.styleable.GenderPicker_genderPickerIconColor,
                Color().toArgb()
            )
        val backgroundColor =
            typedArray.getInteger(
                R.styleable.GenderPicker_genderPickerBackgroundColor,
                R.color.dodgerblue
            )
        val selectedChoice =
            typedArray.getInteger(
                R.styleable.GenderPicker_selectedChoice,
                GenderPicker.DEFAULT_SELECTED_CHOICE
            )

        return GenderPickerAttr(undefinedAvailable, iconColor, backgroundColor, selectedChoice)
    }
}
