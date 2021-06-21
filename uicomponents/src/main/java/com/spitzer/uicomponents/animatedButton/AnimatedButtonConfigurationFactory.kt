package com.spitzer.uicomponents.animatedButton

internal data class AnimatedButtonConfiguration(
    var buttonDefaultColor: Int,
    var buttonSuccessColor: Int,
    var buttonErrorColor: Int,
    var animationDelay: Int
)

internal object AnimatedButtonConfigurationFactory {
    fun create(
        animatedButtonAttr: AnimatedButtonAttr
    ): AnimatedButtonConfiguration {
        return AnimatedButtonConfiguration(
            animatedButtonAttr.buttonDefaultColor,
            animatedButtonAttr.buttonSuccessColor,
            animatedButtonAttr.buttonErrorColor,
            animatedButtonAttr.animationDelay
        )
    }
}
