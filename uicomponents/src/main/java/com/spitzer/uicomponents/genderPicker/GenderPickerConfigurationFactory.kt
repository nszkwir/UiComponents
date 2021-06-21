package com.spitzer.uicomponents.genderPicker

internal data class GenderPickerConfiguration(
    var undefinedAvailable: Boolean,
    var iconColor: Int
)

internal object GenderPickerConfigurationFactory {
    fun create(
        genderPickerAttr: GenderPickerAttr
    ): GenderPickerConfiguration {
        return GenderPickerConfiguration(
            genderPickerAttr.undefinedAvailable,
            genderPickerAttr.iconColor
        )
    }
}
