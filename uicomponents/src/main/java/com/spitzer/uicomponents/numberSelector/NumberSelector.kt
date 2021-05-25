package com.spitzer.uicomponents.numberSelector

import android.content.Context
import android.content.res.ColorStateList
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import com.spitzer.uicomponents.R
import kotlinx.android.synthetic.main.card_view_number_selector.view.*

class NumberSelector : MaterialCardView {
    private lateinit var numberSelectorAttr: NumberSelectorAttr
    private lateinit var numberSelectorConfiguration: NumberSelectorConfiguration
    private lateinit var mLinkingFunction: (Int) -> Unit
    private var isAnimating: Boolean = false

    val DEFAULT_ICON_COLOR: Int = ContextCompat.getColor(context, R.color.lightblue)
    val DEFAULT_DISABLED_ICON_COLOR: Int = ContextCompat.getColor(context, R.color.slategray)
    val DEFAULT_PRESSED_ICON_COLOR: Int = ContextCompat.getColor(context, R.color.greenyellow)
    var minNumber: Int = DEFAULT_MIN_NUMBER
    var maxNumber: Int = DEFAULT_MAX_NUMBER
    var selectedNumber: Int = DEFAULT_SELECTED_NUMBER

    constructor(context: Context) : super(context) {
        val attrs = NumberSelectorAttr(
            minNumber = DEFAULT_MIN_NUMBER,
            maxNumber = DEFAULT_MAX_NUMBER,
            selectedNumber = DEFAULT_SELECTED_NUMBER,
            delay = DEFAULT_DELAY,
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

    private fun initAttrs(attributes: NumberSelectorAttr) {
        numberSelectorAttr = attributes
        val config = NumberSelectorConfigurationFactory.create(context, numberSelectorAttr)
        setupComponents(config)
    }

    private fun initAttrs(attributes: AttributeSet?) {
        numberSelectorAttr = NumberSelectorAttrParser.parse(context, attributes)
        val config = NumberSelectorConfigurationFactory.create(context, numberSelectorAttr)
        setupComponents(config)
    }

    private fun setupComponents(config: NumberSelectorConfiguration) {
        numberSelectorConfiguration = config

        LayoutInflater.from(context).inflate(R.layout.card_view_number_selector, this)
        preventCornerOverlap = true
        useCompatPadding = true
        elevation = resources.getDimension(R.dimen.card_view_normal_elevation)
        radius = resources.getDimension(R.dimen.card_view_normal_radius)

        initiateElements()
        setViewId()
        requestLayout()
    }

    private fun initiateElements() {
        selectedNumber = numberSelectorConfiguration.selectedNumber
        minNumber = numberSelectorConfiguration.minNumber
        maxNumber = numberSelectorConfiguration.maxNumber
        textNumber.text = selectedNumber.toString()

        val colors = intArrayOf(
            DEFAULT_DISABLED_ICON_COLOR,
            DEFAULT_PRESSED_ICON_COLOR,
            numberSelectorConfiguration.iconColor
        )
        val colorStateList = ColorStateList(states, colors)
        btnMinus.apply {
            imageTintList = colorStateList
            setOnClickListener { onMinusPressed() }
        }
        btnPlus.apply {
            imageTintList = colorStateList
            setOnClickListener { onPlusPressed() }
        }
        validate()
    }

    private fun onPlusPressed() {
        validate()
        if (selectedNumber < maxNumber && !isAnimating) {
            isAnimating = true
            val fadeOutDown = AnimationUtils.loadAnimation(context, R.anim.fade_out_slide_down)
            fadeOutDown.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    animation?.duration?.minus(DEFAULT_DELAY)?.let {
                        Handler(Looper.getMainLooper()).postDelayed({
                            selectedNumber++
                            notifyNumberChange()
                        }, it)
                    }
                }

                override fun onAnimationEnd(animation: Animation?) {
                    val fadeInDown =
                        AnimationUtils.loadAnimation(context, R.anim.fade_in_slide_down)
                    textNumber.startAnimation(fadeInDown)
                    validate()
                    isAnimating = false
                }

                override fun onAnimationRepeat(animation: Animation?) = Unit

            })
            textNumber.startAnimation(fadeOutDown)
        }
    }

    private fun onMinusPressed() {
        validate()
        if (selectedNumber > minNumber && !isAnimating) {
            isAnimating = true
            val fadeOutUp = AnimationUtils.loadAnimation(context, R.anim.fade_out_slide_up)
            fadeOutUp.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    animation?.duration?.minus(DEFAULT_DELAY)?.let {
                        Handler(Looper.getMainLooper()).postDelayed({
                            selectedNumber--
                            notifyNumberChange()
                        }, it)
                    }
                }

                override fun onAnimationEnd(animation: Animation?) {
                    val fadeInUp =
                        AnimationUtils.loadAnimation(context, R.anim.fade_in_slide_up)
                    textNumber.startAnimation(fadeInUp)
                    validate()
                    isAnimating = false
                }

                override fun onAnimationRepeat(animation: Animation?) = Unit

            })
            fadeOutUp.fillAfter = true
            textNumber.startAnimation(fadeOutUp)
        }
    }

    private fun validate() {
        btnMinus.isEnabled = selectedNumber > minNumber
        btnPlus.isEnabled = selectedNumber < maxNumber
    }

    fun onValuesChanges(linkingFunction: (Int) -> Unit) {
        mLinkingFunction = linkingFunction
    }

    fun notifyNumberChange() {
        textNumber.text = selectedNumber.toString()
        mLinkingFunction(selectedNumber)
    }

    private fun setViewId() {
        if (id == NO_ID) {
            id = View.generateViewId()
        }
    }

    companion object {
        const val DEFAULT_MIN_NUMBER = 0
        const val DEFAULT_MAX_NUMBER = 99
        const val DEFAULT_SELECTED_NUMBER = 1
        const val DEFAULT_DELAY = 50
        val states = arrayOf(
            intArrayOf(-android.R.attr.state_enabled),
            intArrayOf(android.R.attr.state_pressed),
            intArrayOf(android.R.attr.state_enabled)
        )
    }
}