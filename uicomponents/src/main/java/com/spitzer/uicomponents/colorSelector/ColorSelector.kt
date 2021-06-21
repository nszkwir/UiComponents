package com.spitzer.uicomponents.colorSelector

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.SeekBar
import com.google.android.material.card.MaterialCardView
import com.spitzer.uicomponents.R
import kotlinx.android.synthetic.main.color_selector_card_view.view.*

class ColorSelector : MaterialCardView {
    private lateinit var colorSelectorAttr: ColorSelectorAttr
    private lateinit var colorSelectorConfiguration: ColorSelectorConfiguration
    private lateinit var mLinkingFunction: (Int) -> Unit
    private var colorSelected: Int = 0

    constructor(context: Context) : super(context) {
        val attrs = ColorSelectorAttr(
            startingProgress = STARTING_PROGRESS_VALUE,
            minProgress = MIN_PROGRESS_VALUE,
            maxProgress = MAX_PROGRESS_VALUE
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

    private fun initAttrs(attributes: ColorSelectorAttr) {
        colorSelectorAttr = attributes
        val config = ColorSelectorConfigurationFactory.create(context, colorSelectorAttr)
        setupComponents(config)
    }

    private fun initAttrs(attributes: AttributeSet?) {
        colorSelectorAttr = ColorSelectorAttrParser.parse(context, attributes)
        val config = ColorSelectorConfigurationFactory.create(context, colorSelectorAttr)
        setupComponents(config)
    }

    private fun setupComponents(config: ColorSelectorConfiguration) {
        colorSelectorConfiguration = config
        LayoutInflater.from(context).inflate(R.layout.color_selector_card_view, this)
        preventCornerOverlap = true
        useCompatPadding = true
        elevation = resources.getDimension(R.dimen.card_view_normal_elevation)
        radius = resources.getDimension(R.dimen.card_view_normal_radius)
        initiateSeekBars()
        calculateColor()
        setViewId()
        requestLayout()
    }

    private fun initiateSeekBars() {
        redValueText.text = STARTING_PROGRESS_VALUE.toString()
        greenValueText.text = STARTING_PROGRESS_VALUE.toString()
        blueValueText.text = STARTING_PROGRESS_VALUE.toString()
        alphaValueText.text = STARTING_ALPHA_VALUE.toString()

        seekBarRed.apply {
            progress = STARTING_PROGRESS_VALUE
            progress = STARTING_PROGRESS_VALUE
            min = MIN_PROGRESS_VALUE
            max = MAX_PROGRESS_VALUE
            setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    updateRedTextValue(i.toString())
                    calculateColor()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    notifyColorChange()
                }
            })
//            updateLayoutParams {
//                width = (context.resources.displayMetrics.widthPixels * SEEKBARS_WIDTH_PERCENTAGE).toInt()
//            }
//            requestLayout()
        }

        seekBarGreen.apply {
            min = MIN_PROGRESS_VALUE
            max = MAX_PROGRESS_VALUE
            setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    updateGreenTextValue(i.toString())
                    calculateColor()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    notifyColorChange()
                }
            })
        }

        seekBarBlue.apply {
            min = MIN_PROGRESS_VALUE
            max = MAX_PROGRESS_VALUE
            setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    updateBlueTextValue(i.toString())
                    calculateColor()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    notifyColorChange()
                }
            })
        }
        seekBarAlpha.apply {
            min = MIN_ALPHA_VALUE
            max = MAX_ALPHA_VALUE
            setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    updateAlphaTextValue(i.toString())
                    calculateColor()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    notifyColorChange()
                }
            })
        }
        seekBarRed.progress = STARTING_PROGRESS_VALUE
        seekBarGreen.progress = STARTING_PROGRESS_VALUE
        seekBarBlue.progress = STARTING_PROGRESS_VALUE
        seekBarAlpha.progress = STARTING_ALPHA_VALUE
    }

    private fun updateRedTextValue(value: String) {
        redValueText.text = value
    }

    private fun updateGreenTextValue(value: String) {
        greenValueText.text = value
    }

    private fun updateBlueTextValue(value: String) {
        blueValueText.text = value
    }

    private fun updateAlphaTextValue(value: String) {
        alphaValueText.text = value
    }

    private fun calculateColor() {
        colorSelected = Color.argb(
            seekBarAlpha.progress,
            seekBarRed.progress,
            seekBarGreen.progress,
            seekBarBlue.progress
        )
        colorSampler.setBackgroundColor(colorSelected)
        hexColorText.setText(Integer.toHexString(colorSelected))
    }

    private fun setViewId() {
        if (id == NO_ID) {
            id = View.generateViewId()
        }
    }

    fun setTitle(title: String) {
        titleLabel.text = title
    }

    fun onValuesChanges(linkingFunction: (Int) -> Unit) {
        mLinkingFunction = linkingFunction
    }

    fun notifyColorChange() {
        mLinkingFunction(colorSelected)
    }

    companion object {
        const val MIN_PROGRESS_VALUE = 0
        const val MAX_PROGRESS_VALUE = 255
        const val STARTING_PROGRESS_VALUE = MAX_PROGRESS_VALUE
        const val MIN_ALPHA_VALUE = 0
        const val MAX_ALPHA_VALUE = 255
        const val STARTING_ALPHA_VALUE = MAX_ALPHA_VALUE
        const val SEEKBARS_WIDTH_PERCENTAGE = 0.45f
    }
}