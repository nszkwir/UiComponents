package com.spitzer.uicomponents.numberSelector

internal data class NumberSelectorConfiguration(
    var minNumber: Int,
    var maxNumber: Int,
    var selectedNumber: Int,
    var delay: Int,
    var iconColor: Int
)

internal object NumberSelectorConfigurationFactory {
    fun create(
        numberSelectorAttr: NumberSelectorAttr
    ): NumberSelectorConfiguration {
        return NumberSelectorConfiguration(
            numberSelectorAttr.minNumber,
            numberSelectorAttr.maxNumber,
            numberSelectorAttr.selectedNumber,
            numberSelectorAttr.delay,
            numberSelectorAttr.iconColor
        )
    }
}