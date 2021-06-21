package com.spitzer.uicomponents.animatedButton

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.spitzer.common.ProgressBarAnimation
import com.spitzer.uicomponents.R
import kotlinx.android.synthetic.main.animated_button_constraint_layout.view.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


class AnimatedButton : ConstraintLayout {
    private val buttonDefaultColor = ContextCompat.getColor(context, R.color.dodgerblue)
    private val buttonSuccessColor = ContextCompat.getColor(context, R.color.limegreen)
    private val buttonErrorColor = ContextCompat.getColor(context, R.color.orangered)

    private lateinit var animatedButtonAttr: AnimatedButtonAttr
    private lateinit var animatedButtonConfiguration: AnimatedButtonConfiguration
    private lateinit var onButtonPressedFunction: () -> Unit
    private lateinit var onAnimationEndFunction: () -> Unit

    constructor(context: Context) : super(context) {
        val attrs = AnimatedButtonAttr(
            buttonDefaultColor = buttonDefaultColor,
            buttonSuccessColor = buttonSuccessColor,
            buttonErrorColor = buttonErrorColor,
            animationDelay = ANIMATION_DELAY
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

    private fun initAttrs(attributes: AnimatedButtonAttr) {
        animatedButtonAttr = attributes
        val config = AnimatedButtonConfigurationFactory.create(animatedButtonAttr)
        setupComponents(config)
    }

    private fun initAttrs(attributes: AttributeSet?) {
        animatedButtonAttr = AnimatedButtonAttrParser.parse(context, attributes)
        val config = AnimatedButtonConfigurationFactory.create(animatedButtonAttr)
        setupComponents(config)
    }

    private fun setupComponents(config: AnimatedButtonConfiguration) {
        animatedButtonConfiguration = config
        LayoutInflater.from(context).inflate(R.layout.animated_button_constraint_layout, this)
        initiateElements()
        setViewId()
        requestLayout()
    }

    private fun initiateElements() {
        button_animated_button.setOnClickListener {
            startLoading()
            onButtonPressedFunction()
        }
    }

    fun setOnButtonPressedFunction(linkFunction: () -> Unit) {
        onButtonPressedFunction = linkFunction
    }

    fun setOnAnimationEndFunction(linkFunction: () -> Unit) {
        onAnimationEndFunction = linkFunction
    }

    private fun startLoading() {
        progress_bar_animated_button.visibility = View.VISIBLE
        val anim = ProgressBarAnimation(progress_bar_animated_button, PROGRESS_MIN, PROGRESS_MAX)
        anim.duration = PROGRESS_SLOW
        progress_bar_animated_button.startAnimation(anim)
    }

    fun continueSuccessAnimation() {
        val progress = progress_bar_animated_button.progress
        if (progress < PROGRESS_MAX) {
            progress_bar_animated_button.clearAnimation()
            val anim = ProgressBarAnimation(
                progress_bar_animated_button,
                progress.toFloat(),
                PROGRESS_MAX
            )
            anim.duration = PROGRESS_FAST
            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) = Unit

                override fun onAnimationEnd(animation: Animation?) {
                    playSuccessAnimation()
                }

                override fun onAnimationRepeat(animation: Animation?) = Unit
            })
            progress_bar_animated_button.startAnimation(anim)
        } else {
            playSuccessAnimation()
        }
    }

    fun continueErrorAnimation() {
        val progress = progress_bar_animated_button.progress
        if (progress < PROGRESS_MAX) {
            progress_bar_animated_button.clearAnimation()
            val anim = ProgressBarAnimation(
                progress_bar_animated_button,
                progress.toFloat(),
                PROGRESS_MAX
            )
            anim.duration = PROGRESS_FAST
            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) = Unit

                override fun onAnimationEnd(animation: Animation?) {
                    playErrorAnimation()
                }

                override fun onAnimationRepeat(animation: Animation?) = Unit
            })
            progress_bar_animated_button.startAnimation(anim)
        } else {
            playErrorAnimation()
        }
        progress_bar_animated_button.visibility = View.INVISIBLE
    }

    @SuppressLint("Recycle")
    private fun playSuccessAnimation() {
        progress_bar_animated_button.visibility = View.INVISIBLE
        text_animated_button.visibility = View.INVISIBLE
        button_animated_button.background = ResourcesCompat.getDrawable(
            resources,
            R.drawable.animated_button_background_success_shape,
            null
        )

        val fromRadius = resources.getDimension(R.dimen.animated_button_from_radius)
        val toRadius = button_animated_button.height / 2f
        val fromWidth = button_animated_button.width
        val toWidth = button_animated_button.height
        val fromColor = buttonDefaultColor
        val toColor = buttonSuccessColor
        val gradientDrawable = button_animated_button.background as GradientDrawable

        val cornerAnimator = ValueAnimator.ofFloat(fromRadius, toRadius)
        val widthAnimator = ValueAnimator.ofInt(fromWidth, toWidth)
        val colorAnimator = ValueAnimator.ofObject(ArgbEvaluator(), fromColor, toColor)

        cornerAnimator.apply {
            duration = PROGRESS_FAST
            addUpdateListener { animation ->
                gradientDrawable.cornerRadius = animation.animatedValue as Float
            }
        }
        widthAnimator.apply {
            duration = PROGRESS_FAST
            addUpdateListener { animation ->
                button_animated_button.layoutParams.width = animation.animatedValue as Int
                button_animated_button.requestLayout()
            }
        }
        colorAnimator.apply {
            duration = PROGRESS_FAST
            addUpdateListener { animation ->
                gradientDrawable.color = ColorStateList.valueOf(animation.animatedValue as Int)
                button_animated_button.background = gradientDrawable
                button_animated_button.requestLayout()
            }
        }

        cornerAnimator.start()
        widthAnimator.start()
        colorAnimator.start()

        colorAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) = Unit

            override fun onAnimationEnd(animation: Animator?) {
                onAnimationEndFunction()
            }

            override fun onAnimationCancel(animation: Animator?) = Unit

            override fun onAnimationRepeat(animation: Animator?) = Unit
        })
    }

    @SuppressLint("Recycle")
    private fun playErrorAnimation() {
        progress_bar_animated_button.visibility = View.INVISIBLE
        button_animated_button.background = ResourcesCompat.getDrawable(
            resources,
            R.drawable.animated_button_background_error_shape,
            null
        )

        val fromColor = buttonDefaultColor
        val toColor = buttonErrorColor
        val gradientDrawable = button_animated_button.background as GradientDrawable

        val colorAnimator = ValueAnimator.ofObject(ArgbEvaluator(), fromColor, toColor)

        colorAnimator.apply {
            duration = PROGRESS_FAST
            addUpdateListener { animation ->
                gradientDrawable.color = ColorStateList.valueOf(animation.animatedValue as Int)
            }
        }

        colorAnimator.start()

        colorAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) = Unit

            override fun onAnimationEnd(animation: Animator?) {
                shakeButton()
            }

            override fun onAnimationCancel(animation: Animator?) = Unit

            override fun onAnimationRepeat(animation: Animator?) = Unit
        })
    }

    fun getErrorButtonToInitialState() {
        progress_bar_animated_button.progress = 0
        button_animated_button.isEnabled = false
        progress_bar_animated_button.visibility = View.INVISIBLE
        button_animated_button.background = ResourcesCompat.getDrawable(
            resources,
            R.drawable.animated_button_background_error_shape,
            null
        )

        val fromColor = buttonErrorColor
        val toColor = buttonDefaultColor
        val gradientDrawable = button_animated_button.background as GradientDrawable

        val colorAnimator = ValueAnimator.ofObject(ArgbEvaluator(), fromColor, toColor)

        colorAnimator.apply {
            duration = PROGRESS_FAST
            addUpdateListener { animation ->
                gradientDrawable.color = ColorStateList.valueOf(animation.animatedValue as Int)
            }
        }

        colorAnimator.start()

        colorAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                runBlocking {
                    delay(2000)
                }
            }

            override fun onAnimationEnd(animation: Animator?) {
                button_animated_button.isEnabled = true
                onAnimationEndFunction()
            }

            override fun onAnimationCancel(animation: Animator?) = Unit

            override fun onAnimationRepeat(animation: Animator?) = Unit
        })
    }

    fun shakeButton() {
        val shake: Animation = AnimationUtils.loadAnimation(context, R.anim.shake_one)
        shake.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) = Unit

            override fun onAnimationEnd(animation: Animation?) {
                getErrorButtonToInitialState()
            }

            override fun onAnimationRepeat(animation: Animation?) = Unit

        })
        button_animated_button.startAnimation(shake)
    }

    private fun setViewId() {
        if (id == NO_ID) {
            id = View.generateViewId()
        }
    }

    companion object {
        const val ANIMATION_DELAY = 20
        const val PROGRESS_MIN = 0f
        const val PROGRESS_MAX = 100f
        const val PROGRESS_FAST = 500L
        const val PROGRESS_SLOW = 4000L
    }
}
