package com.spitzer.uicomponents.extensions

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.view.View

fun View.collapseView(
    from: Int,
    to: Int,
    duration: Int,
    delay: Int
): AnimatorSet {
    val slideAnimation = ValueAnimator
        .ofInt(from, to)
        .setDuration(duration.toLong())
    slideAnimation.startDelay = delay.toLong()
    slideAnimation.addUpdateListener { animation: ValueAnimator ->
        layoutParams?.height = (animation.animatedValue as Int)
        requestLayout()
    }
    val set = AnimatorSet()
    set.play(slideAnimation)
    return set
}

fun View.measureViewHeight(): Int {
    val widthSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY)
    val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    measure(widthSpec, heightSpec)
    return measuredHeight
}