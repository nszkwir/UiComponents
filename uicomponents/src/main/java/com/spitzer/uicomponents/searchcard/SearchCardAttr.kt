package com.spitzer.uicomponents.searchcard

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.spitzer.uicomponents.R

internal data class SearchCardAttr(
    val hintText: String,
    val searchCardRadius: Int,
    val editableText: Boolean,
    val showDrawableEnd: Boolean,
    val drawableEnd: Drawable,
    val drawableEndColor: Int,
    val showCompactDesign: Boolean,
    val searchCardTextSize: Int
)

internal object SearchCardAttrParser {
    @SuppressLint("Recycle")
    fun parse(context: Context, attr: AttributeSet?): SearchCardAttr {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.SearchCard)

        var hintText = typedArray.getString(R.styleable.SearchCard_hintText)
        if (hintText == null) hintText = SearchCard.DEFAULT_HINT_TEXT

        val showDrawableEnd =
            typedArray.getBoolean(
                R.styleable.SearchCard_showDrawableEnd,
                SearchCard.DEFAULT_SHOW_DRAWABLE_END
            )
        val searchCardRadius = typedArray.getInteger(
            R.styleable.SearchCard_searchCardRadius,
            SearchCard.DEFAULT_SEARCH_CARD_RADIUS
        )
        val editableText =
            typedArray.getBoolean(
                R.styleable.SearchCard_editableText,
                SearchCard.DEFAULT_EDITABLE_TEXT
            )
        var drawableEnd =
            typedArray.getDrawable(R.styleable.SearchCard_drawableEnd)

        if (drawableEnd == null) drawableEnd =
            ContextCompat.getDrawable(context, SearchCard.DEFAULT_DRAWABLE_END)

        val drawableEndColor =
            typedArray.getColor(
                R.styleable.SearchCard_drawableEndColor,
                SearchCard.DEFAULT_DRAWABLE_END_COLOR
            )
        val showCompactDesign =
            typedArray.getBoolean(
                R.styleable.SearchCard_showCompactDesign,
                SearchCard.DEFAULT_SHOW_COMPACT_DESIGN
            )

        val searchCardTextSize =
            typedArray.getInteger(
                R.styleable.SearchCard_searchCardTextSize,
                SearchCard.DEFAULT_SEARCH_CARD_TEXT_SIZE
            )

        return SearchCardAttr(
            hintText,
            searchCardRadius,
            editableText,
            showDrawableEnd,
            drawableEnd!!,
            drawableEndColor,
            showCompactDesign,
            searchCardTextSize
        )
    }
}
