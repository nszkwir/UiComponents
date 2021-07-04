package com.spitzer.uicomponents.searchcard

import android.graphics.drawable.Drawable

internal data class SearchCardConfiguration(
    var hintText: String,
    var searchCardRadius: Int,
    var editableText: Boolean,
    var showDrawableEnd: Boolean,
    var drawableEnd: Drawable,
    var drawableEndColor: Int,
    var showCompactDesign: Boolean,
    var searchCardTextSize: Int
)

internal object SearchCardConfigurationFactory {
    fun create(
        searchCardAttr: SearchCardAttr
    ): SearchCardConfiguration {
        return SearchCardConfiguration(
            searchCardAttr.hintText,
            searchCardAttr.searchCardRadius,
            searchCardAttr.editableText,
            searchCardAttr.showDrawableEnd,
            searchCardAttr.drawableEnd,
            searchCardAttr.drawableEndColor,
            searchCardAttr.showCompactDesign,
            searchCardAttr.searchCardTextSize
        )
    }
}
