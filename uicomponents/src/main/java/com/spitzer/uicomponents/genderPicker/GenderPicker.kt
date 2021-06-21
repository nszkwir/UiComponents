package com.spitzer.uicomponents.genderPicker

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.spitzer.uicomponents.R
import kotlinx.android.synthetic.main.gender_picker_card_view.view.*

// TODO extender las funcionalidades para customizar colores tanto de fondo como texto desde atttrs
class GenderPicker : CardView {

    private lateinit var genderPickerAttr: GenderPickerAttr
    private lateinit var genderPickerConfiguration: GenderPickerConfiguration
    private lateinit var mLinkingFunction: (Int) -> Unit

    private val DEFAULT_ICON_COLOR: Int =
        ContextCompat.getColor(context, R.color.lightblue)
    private val DEFAULT_DISABLED_ICON_COLOR: Int =
        ContextCompat.getColor(context, R.color.slategray)
    private val DEFAULT_PRESSED_ICON_COLOR: Int =
        ContextCompat.getColor(context, R.color.greenyellow)

    constructor(context: Context) : super(context) {
        val attrs = GenderPickerAttr(
            undefinedAvailable = DEFAULT_UNDEFINED_AVAILABLE,
            iconColor = DEFAULT_ICON_COLOR
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

    private fun initAttrs(attributes: GenderPickerAttr) {
        genderPickerAttr = attributes
        val config = GenderPickerConfigurationFactory.create(genderPickerAttr)
        setupComponents(config)
    }

    private fun initAttrs(attributes: AttributeSet?) {
        genderPickerAttr = GenderPickerAttrParser.parse(context, attributes)
        val config = GenderPickerConfigurationFactory.create(genderPickerAttr)
        setupComponents(config)
    }

    private fun setupComponents(config: GenderPickerConfiguration) {
        genderPickerConfiguration = config

        LayoutInflater.from(context).inflate(R.layout.gender_picker_card_view, this)
        preventCornerOverlap = true
        useCompatPadding = true
        elevation = resources.getDimension(R.dimen.card_view_normal_elevation)
        radius = resources.getDimension(R.dimen.card_view_normal_radius)

        initiateElements()
        setViewId()
        requestLayout()
    }

    private fun initiateElements() {
        // TODO validate if should show or hide Undefined genre button
        genderPickerRadioGroup.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.radio0 -> {
                    mLinkingFunction(0)
                }
                R.id.radio1 -> {
                    mLinkingFunction(1)
                }
                R.id.radio2 -> {
                    mLinkingFunction(2)
                }
                else -> {
                    mLinkingFunction(-1)
                }
            }
        }

    }

    fun onValuesChanges(linkingFunction: (Int) -> Unit) {
        mLinkingFunction = linkingFunction
    }

    private fun setViewId() {
        if (id == NO_ID) {
            id = View.generateViewId()
        }
    }

    companion object {
        const val DEFAULT_UNDEFINED_AVAILABLE = false
        val states = arrayOf(
            intArrayOf(-android.R.attr.state_enabled),
            intArrayOf(android.R.attr.state_pressed),
            intArrayOf(android.R.attr.state_enabled)
        )
    }
}
