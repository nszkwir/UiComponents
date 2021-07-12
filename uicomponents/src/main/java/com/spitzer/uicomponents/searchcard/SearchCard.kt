package com.spitzer.uicomponents.searchcard

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.google.android.material.card.MaterialCardView
import com.spitzer.uicomponents.R
import com.spitzer.uicomponents.extensions.dp
import com.spitzer.uicomponents.extensions.setRightDrawable
import kotlinx.android.synthetic.main.search_card_view.view.*

class SearchCard : MaterialCardView {
    private lateinit var searchCardAttr: SearchCardAttr
    private lateinit var configuration: SearchCardConfiguration
    private lateinit var onClickLinkingFunction: () -> Unit
    private lateinit var onTextChangeLinkingFunction: (
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) -> Unit


    constructor(context: Context) : super(context) {
        val attrs = SearchCardAttr(
            hintText = DEFAULT_HINT_TEXT,
            searchCardRadius = DEFAULT_SEARCH_CARD_RADIUS,
            editableText = DEFAULT_EDITABLE_TEXT,
            showDrawableEnd = DEFAULT_SHOW_DRAWABLE_END,
            drawableEnd = ContextCompat.getDrawable(context, DEFAULT_DRAWABLE_END)!!,
            drawableEndColor = DEFAULT_DRAWABLE_END_COLOR,
            showCompactDesign = DEFAULT_SHOW_COMPACT_DESIGN,
            searchCardTextSize = DEFAULT_SEARCH_CARD_TEXT_SIZE
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

    private fun initAttrs(attributes: SearchCardAttr) {
        searchCardAttr = attributes
        val config = SearchCardConfigurationFactory.create(searchCardAttr)
        setupComponents(config)
    }

    private fun initAttrs(attributes: AttributeSet?) {
        searchCardAttr = SearchCardAttrParser.parse(context, attributes)
        val config = SearchCardConfigurationFactory.create(searchCardAttr)
        setupComponents(config)
    }

    private fun setupComponents(config: SearchCardConfiguration) {
        configuration = config

        LayoutInflater.from(context).inflate(R.layout.search_card_view, this)
        preventCornerOverlap = true
        useCompatPadding = true
        elevation = resources.getDimension(R.dimen.search_card_view_elevation)
        radius = config.searchCardRadius.dp.toFloat()

        if (config.showCompactDesign) {
            if (config.showDrawableEnd) {
                val colors = intArrayOf(config.drawableEndColor, config.drawableEndColor)
                val colorStateList = ColorStateList(states, colors)
                searchText.apply {
                    setRightDrawable(config.drawableEnd)
                    compoundDrawableTintList = colorStateList
                }
            } else {
                searchText.setRightDrawable(null)
            }
            searchText.apply {
                visibility = VISIBLE
                hint = config.hintText
                isCursorVisible = false
                focusable = NOT_FOCUSABLE
                setTextSize(TypedValue.COMPLEX_UNIT_SP, config.searchCardTextSize.toFloat())
                setOnClickListener {
                    onClickLinkingFunction()
                }
            }
            searchTextInput.visibility = GONE
        } else {
            searchTextInput.apply {
                visibility = VISIBLE
                hint = config.hintText
                setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    config.searchCardTextSize.toFloat()
                )
            }
            searchText.visibility = GONE
            if (config.showDrawableEnd) {
                val colors = intArrayOf(config.drawableEndColor, config.drawableEndColor)
                val colorStateList = ColorStateList(states, colors)
                searchTextInput.apply {
                    setRightDrawable(config.drawableEnd)
                    compoundDrawableTintList = colorStateList
                }
            } else {
                searchTextInput.setRightDrawable(null)
            }
            if (config.editableText) {
                searchTextInput.apply {
                    isCursorVisible = true
                    focusable = FOCUSABLE
                    doOnTextChanged { text, start, before, count ->
                        onTextChangeLinkingFunction(text, start, before, count)
                    }
                }
            } else {
                searchTextInput.apply {
                    isCursorVisible = false
                    focusable = NOT_FOCUSABLE
                    setOnClickListener {
                        onClickLinkingFunction()
                    }
                }
            }
        }

        setViewId()
        requestLayout()
    }

    fun onClickFunction(linkingFunction: () -> Unit) {
        onClickLinkingFunction = linkingFunction
    }

    fun onTextChangedFunction(
        onTextChangedFunction: (
            text: CharSequence?,
            start: Int,
            before: Int,
            count: Int
        ) -> Unit
    ) {
        onTextChangeLinkingFunction = onTextChangedFunction
    }

    private fun setViewId() {
        if (id == NO_ID) {
            id = View.generateViewId()
        }
    }

    fun setText(newText: String) {
        if (searchTextInput.visibility == View.VISIBLE) {
            searchTextInput.setText(newText)
        } else {
            searchText.text = newText
        }
    }

    companion object {
        const val DEFAULT_HINT_TEXT = "Search ..."
        const val DEFAULT_EDITABLE_TEXT = false
        const val DEFAULT_SHOW_DRAWABLE_END = true
        const val DEFAULT_SEARCH_CARD_RADIUS = 16
        const val DEFAULT_SHOW_COMPACT_DESIGN = false
        const val DEFAULT_SEARCH_CARD_TEXT_SIZE = 14

        var DEFAULT_DRAWABLE_END = R.drawable.ic_search_24
        var DEFAULT_DRAWABLE_END_COLOR = R.color.dodgerblue

        val states = arrayOf(
            intArrayOf(-android.R.attr.state_enabled),
            intArrayOf(android.R.attr.state_enabled)
        )
    }
}
