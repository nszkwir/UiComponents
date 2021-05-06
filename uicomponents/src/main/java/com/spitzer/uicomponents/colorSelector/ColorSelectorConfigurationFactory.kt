package com.spitzer.uicomponents.colorSelector

import android.content.Context

internal data class ColorSelectorConfiguration(
    var startingProgress: Int,
    var minProgress: Int,
    var maxProgress: Int
)

internal object ColorSelectorConfigurationFactory {
    fun create(context: Context, colorSelectorAttr: ColorSelectorAttr): ColorSelectorConfiguration {
        return with(colorSelectorAttr) {
            ColorSelectorConfiguration(
                colorSelectorAttr.startingProgress,
                colorSelectorAttr.minProgress,
                colorSelectorAttr.maxProgress
            )
        }
    }
}