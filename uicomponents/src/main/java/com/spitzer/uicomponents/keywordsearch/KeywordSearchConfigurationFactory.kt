package com.spitzer.uicomponents.keywordsearch

internal data class KeywordSearchConfiguration(
    var hintText: String,
    var backgroundColor: Int,
    var drawableColor: Int
)

internal object KeywordSearchConfigurationFactory {
    fun create(
        keywordSearchAttr: KeywordSearchAttr
    ): KeywordSearchConfiguration {
        return KeywordSearchConfiguration(
            keywordSearchAttr.hintText,
            keywordSearchAttr.backgroundColor,
            keywordSearchAttr.drawableColor
        )
    }
}
