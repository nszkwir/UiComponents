package com.spitzer.uicomponents

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.spitzer.uicomponents.colorSelector.ColorSelector

class NonFragmentViewPagerAdapter(
    private val context: Context,
    private val viewList: ArrayList<ColorSelector>) :
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