package com.spitzer.uicomponents.genderPicker

import android.content.Context
import android.content.res.ColorStateList
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

    private val defaultIconColor: Int =
        ContextCompat.getColor(context, R.color.lightblue)
    private val defaultDisabledIconColor: Int =
        ContextCompat.getColor(context, R.color.slategray)
    private val defaultPressedIconColor: Int =
        ContextCompat.getColor(context, R.color.greenyellow)
    private val defaultEnabledBackgroundColor: Int =
        ContextCompat.getColor(context, R.color.white)
    private val defaultDisabledBackgroundColor: Int =
        ContextCompat.getColor(context, R.color.slategray)
    private val defaultPressedBackgroundColor: Int =
        ContextCompat.getColor(context, R.color.dodgerblue)

    constructor(context: Context) : super(context) {
        val attrs = GenderPickerAttr(
            undefinedAvailable = DEFAULT_UNDEFINED_AVAILABLE,
            iconColor = defaultIconColor,
            backgroundColor = defaultPressedBackgroundColor,
            defaultSelectedChoice = DEFAULT_SELECTED_CHOICE
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

        initiateElements(config)
        setViewId()
        requestLayout()
    }

    private fun initiateElements(config: GenderPickerConfiguration) {
        // TODO allow to set backgound & font colors for selected/non selected
        val colors = intArrayOf(config.backgroundColor)
        val colorStateList = ColorStateList(states, colors)
        radio0.backgroundTintList = colorStateList
        radio1.backgroundTintList = colorStateList
        radio2.backgroundTintList = colorStateList


        genderPickerRadioGroup.radio2.visibility = when (config.undefinedAvailable) {
            true -> View.VISIBLE
            else -> View.GONE
        }
        genderPickerRadioGroup.check(
            when (config.selectedChoice) {
                0 -> R.id.radio0
                1 -> R.id.radio1
                2 -> R.id.radio2
                else -> R.id.radio0
            }
        )
        genderPickerRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            getSelectedValueById(checkedId)
        }
    }

    private fun getSelectedValueById(radioButtonId: Int) {
        when (radioButtonId) {
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

    fun onValuesChanges(linkingFunction: (Int) -> Unit) {
        mLinkingFunction = linkingFunction
        getSelectedValueById(genderPickerRadioGroup.checkedRadioButtonId)
    }

    private fun setViewId() {
        if (id == NO_ID) {
            id = View.generateViewId()
        }
    }

    companion object {
        const val DEFAULT_UNDEFINED_AVAILABLE = false
        const val DEFAULT_SELECTED_CHOICE = 0
        val states = arrayOf(intArrayOf(android.R.attr.state_checked))
    }
}
