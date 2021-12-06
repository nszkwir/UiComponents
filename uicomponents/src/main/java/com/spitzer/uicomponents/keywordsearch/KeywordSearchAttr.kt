package com.spitzer.uicomponents.keywordsearch

import android.content.Context
import android.util.AttributeSet
import com.spitzer.uicomponents.R
import com.spitzer.uicomponents.keywordsearch.KeywordSearch.Companion.DEFAULT_BACKGROUND_COLOR
import com.spitzer.uicomponents.keywordsearch.KeywordSearch.Companion.DEFAULT_DRAWABLE_COLOR

internal data class KeywordSearchAttr(
    val hintText: String,
    val backgroundColor: Int,
    val drawableColor: Int
)

internal object KeywordSearchAttrParser {
    fun parse(context: Context, attr: AttributeSet?): KeywordSearchAttr {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.KeywordSearch)

        var hintText = typedArray.getString(R.styleable.KeywordSearch_keywordSearchHintText)
        if (hintText == null) hintText = KeywordSearch.DEFAULT_HINT_TEXT

        val backgroundColor =
            typedArray.getInteger(
                R.styleable.KeywordSearch_keywordSearchBackgroundColor,
                DEFAULT_BACKGROUND_COLOR//Color().toArgb()
            )


        val drawableColor =
            typedArray.getColor(
                R.styleable.KeywordSearch_keywordSearchDrawableColor,
                DEFAULT_DRAWABLE_COLOR//Color().toArgb()
            )

        typedArray.recycle()

        return KeywordSearchAttr(
            hintText,
            backgroundColor,
            drawableColor
        )
    }
}
