package com.spitzer.uicomponents.keywordsearch

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.spitzer.uicomponents.R
import kotlinx.android.synthetic.main.keyword_search_view.view.*
import kotlinx.android.synthetic.main.number_selector_card_view.view.*

class KeywordSearch : ConstraintLayout {
    private lateinit var keywordSearchAttr: KeywordSearchAttr
    private lateinit var configuration: KeywordSearchConfiguration

    private lateinit var onSearchFunction: (String) -> Unit
    private lateinit var onErrorFunction: (String) -> Unit

    private lateinit var adapter: KeywordAdapter

    constructor(context: Context) : super(context) {
        val attrs = KeywordSearchAttr(
            hintText = DEFAULT_HINT_TEXT,
            drawableColor = DEFAULT_DRAWABLE_COLOR,
            backgroundColor = DEFAULT_BACKGROUND_COLOR
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

    private fun initAttrs(attributes: KeywordSearchAttr) {
        keywordSearchAttr = attributes
        val config = KeywordSearchConfigurationFactory.create(keywordSearchAttr)
        setupComponents(config)
    }

    private fun initAttrs(attributes: AttributeSet?) {
        keywordSearchAttr = KeywordSearchAttrParser.parse(context, attributes)
        val config = KeywordSearchConfigurationFactory.create(keywordSearchAttr)
        setupComponents(config)
    }

    private fun setupComponents(config: KeywordSearchConfiguration) {
        configuration = config

        LayoutInflater.from(context).inflate(R.layout.keyword_search_view, this)

        val drawableColors = intArrayOf(
            configuration.drawableColor,
            configuration.drawableColor,
            configuration.drawableColor
        )
        val backgroundColors = intArrayOf(
            configuration.backgroundColor,
            configuration.backgroundColor,
            configuration.backgroundColor
        )
        val drawableColorStateList = ColorStateList(states, drawableColors)
        val backgroundColorStateList = ColorStateList(states, backgroundColors)

        add_keyword_button.imageTintList = drawableColorStateList
        search_button.imageTintList = drawableColorStateList
        seachLayout.backgroundTintList = backgroundColorStateList
        search_background.backgroundTintList = backgroundColorStateList

        adapter = KeywordAdapter(
            drawableColorStateList,
            backgroundColorStateList
        )
        keyword_recyclerview.adapter = adapter

        addKeywordText.apply {
            hint = configuration.hintText
            setOnKeyListener { _, keyCode, event ->
                // TODO analize if its possible to add other keys or event to trigger this code
                return@setOnKeyListener if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    val keyword = addKeywordText.text.toString()
                    addKeywordText.text?.clear()

                    // TODO handle validations and errors in a proper way
                    if (validateKeyword(keyword)) {

                        if (adapter.addKeyword(keyword.trim())) {
                            true
                        } else {
                            onErrorFunction("Cant add more keywords")
                            false
                        }
                    } else {
                        onErrorFunction("Keyword not valid")
                        false
                    }
                } else {
                    false
                }
            }
        }

        search_button.setOnClickListener {
            onSearchFunction(adapter.getKeywordsString())
        }

        add_keyword_button.setOnClickListener {
            val keyword = addKeywordText.text.toString()
            addKeywordText.text?.clear()

            if (validateKeyword(keyword)) {
                if (!adapter.addKeyword(keyword.trim())) {
                    onErrorFunction("Cant add more keywords")
                }
            } else {
                onErrorFunction("Keyword not valid")
            }
        }
        setViewId()
        requestLayout()
    }

    private fun validateKeyword(keyword: String): Boolean {
        // TODO define consistent rules
        val auxString = keyword.trim()
        return when {
            auxString.isBlank() -> false
            auxString.length < 3 -> false
            auxString.length > 40 -> false
            else -> true
        }
    }


    fun setOnSearchFunction(linkingFunction: (String) -> Unit) {
        onSearchFunction = linkingFunction
    }

    fun setOnErrorMessage(linkingFunction: (String) -> Unit) {
        onErrorFunction = linkingFunction
    }

    private fun setViewId() {
        if (id == NO_ID) {
            id = View.generateViewId()
        }
    }

    companion object {
        const val DEFAULT_HINT_TEXT = "Add keywords to search"
        var DEFAULT_BACKGROUND_COLOR = R.color.cadetblue
        var DEFAULT_DRAWABLE_COLOR = R.color.white
        val states = arrayOf(
            intArrayOf(-android.R.attr.state_enabled),
            intArrayOf(android.R.attr.state_pressed),
            intArrayOf(android.R.attr.state_enabled)
        )
    }
}
