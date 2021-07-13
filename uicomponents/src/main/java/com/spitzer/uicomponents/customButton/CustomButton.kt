package com.spitzer.uicomponents.customButton

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.google.android.material.button.MaterialButton
import com.spitzer.uicomponents.R


class CustomButton : MaterialButton {

    private lateinit var customButtonAttr: CustomButtonAttr
    private lateinit var customButtonConfiguration: CustomButtonConfiguration

    constructor(context: Context) : super(context) {
        val attrs = CustomButtonAttr(
            primaryColor = DEFAULT_PRIMARY_COLOR,
            secondaryColor = DEFAULT_SECONDARY_COLOR,
            isOutline = IS_OUTLINE_DEFAULT_VALUE,
            cornerRadius = DEFAULT_CORNER_RADIUS,
            strokeWidth = DEFAULT_STROKE_WIDTH
        )
        initAttrs(attrs)
    }

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes) {
        initAttrs(attributes)
    }

    constructor(context: Context, attributes: AttributeSet, defStyle: Int) : super(
        context,
        attributes,
        defStyle
    ) {
        initAttrs(attributes)
    }

    private fun initAttrs(attributes: CustomButtonAttr) {
        customButtonAttr = attributes
        val config = CustomButtonConfigurationFactory.create(customButtonAttr)
        setupComponents(config)
    }

    private fun initAttrs(attributes: AttributeSet?) {
        customButtonAttr = CustomButtonAttrParser.parse(context, attributes)
        val config = CustomButtonConfigurationFactory.create(customButtonAttr)
        setupComponents(config)
    }

    private fun setupComponents(config: CustomButtonConfiguration) {
        customButtonConfiguration = config

        highlightColor
        //rippleColor = ColorStateList()
        if (config.isOutline) {

        } else {

        }

        setViewId()
        requestLayout()
    }

    private fun setViewId() {
        if (id == NO_ID) {
            id = View.generateViewId()
        }
    }

    companion object {
        val DEFAULT_PRIMARY_COLOR = R.color.dodgerblue
        val DEFAULT_SECONDARY_COLOR = R.color.white
        const val IS_OUTLINE_DEFAULT_VALUE = false
        const val DEFAULT_CORNER_RADIUS = 4
        const val DEFAULT_STROKE_WIDTH = 1
    }
}
