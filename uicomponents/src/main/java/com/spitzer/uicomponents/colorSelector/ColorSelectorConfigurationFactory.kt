package com.spitzer.uicomponents.colorSelector

internal data class ColorSelectorConfiguration(
    var startingProgress: Int,
    var minProgress: Int,
    var maxProgress: Int
)

internal object ColorSelectorConfigurationFactory {
    fun create(colorSelectorAttr: ColorSelectorAttr): ColorSelectorConfiguration {
        return ColorSelectorConfiguration(
            colorSelectorAttr.startingProgress,
            colorSelectorAttr.minProgress,
            colorSelectorAttr.maxProgress
        )
    }
}
