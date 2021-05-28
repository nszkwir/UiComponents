package com.spitzer.uicomponents.numberSelector

import android.content.Context

internal data class NumberSelectorConfiguration(
    var minNumber: Int,
    var maxNumber: Int,
    var selectedNumber: Int,
    var delay: Int,
    var iconColor: Int
)

internal object NumberSelectorConfigurationFactory {
    fun create(
        context: Context,
        numberSelectorAttr: NumberSelectorAttr
    ): NumberSelectorConfiguration {
        return with(numberSelectorAttr) {
            NumberSelectorConfiguration(
                numberSelectorAttr.minNumber,
                numberSelectorAttr.maxNumber,
                numberSelectorAttr.selectedNumber,
                numberSelectorAttr.delay,
                numberSelectorAttr.iconColor
            )
        }
    }
}