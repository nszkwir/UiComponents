package com.spitzer.uicomponents.customButton

internal data class CustomButtonConfiguration(
    var primaryColor: Int,
    var secondaryColor: Int,
    var isOutline: Boolean,
    var cornerRadius: Int,
    var strokeWidth: Int
)

internal object CustomButtonConfigurationFactory {
    fun create(
        customButtonAttr: CustomButtonAttr
    ): CustomButtonConfiguration {
        return CustomButtonConfiguration(
            customButtonAttr.primaryColor,
            customButtonAttr.secondaryColor,
            customButtonAttr.isOutline,
            customButtonAttr.cornerRadius,
            customButtonAttr.strokeWidth
        )
    }
}
