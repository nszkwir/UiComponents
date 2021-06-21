package com.spitzer.uicomponents.colorSelector

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class ColorSelectorViewPagerAdapter(
    private val viewList: ArrayList<ColorSelector>
) :
    PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = viewList[position]
        container.addView(view)
        return view
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount() = viewList.size
    override fun isViewFromObject(view: View, `object`: Any) = view === `object`
}
