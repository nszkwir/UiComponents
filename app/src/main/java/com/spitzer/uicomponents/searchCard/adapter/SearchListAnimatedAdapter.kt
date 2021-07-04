package com.spitzer.uicomponents.searchCard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.spitzer.uicomponents.R
import com.spitzer.uicomponents.extensions.listenToClick
import com.spitzer.uicomponents.searchCard.data.SearchDataModel
import kotlinx.android.synthetic.main.rowitem_search.view.*
import java.util.*
import kotlin.collections.ArrayList

class SearchListAnimatedAdapter(val searchDataList: ArrayList<SearchDataModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var filterList = ArrayList<SearchDataModel>()
    var showList = ArrayList<SearchDataModel>()
    lateinit var onItemClick: (SearchDataModel) -> Unit

    private lateinit var mRecyclerView: RecyclerView

    init {
        filterList.addAll(searchDataList)
        showList.addAll(searchDataList)
    }

    fun onItemClickFunction(itemClickFunction: (SearchDataModel) -> Unit) {
        onItemClick = itemClickFunction
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(showList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rowitem_search, parent, false)
        return ViewHolder(view).listenToClick { position, _ -> onItemClick(showList[position]) }
    }

    override fun getItemCount(): Int {
        return showList.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: SearchDataModel) {
            itemView.textViewSearchNombre.text = model.nombre + ","
            itemView.textViewSearchApellido.text = model.apellido
            itemView.textViewSearchDNI.text = model.dni
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filterList = searchDataList
                } else {
                    val resultList = ArrayList<SearchDataModel>()
                    for (item in searchDataList) {
                        val str: String = item.nombre + " " + item.apellido + " " + item.dni
                        if (str.lowercase(Locale.getDefault()).contains(
                                constraint.toString()
                                    .lowercase(Locale.getDefault())
                            )
                        ) {
                            resultList.add(item)
                        }
                    }
                    filterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<SearchDataModel>
                animateList()
                mRecyclerView.scrollToPosition(0)
            }
        }
    }

    // TODO logica compleja y costosa para animar el filtro
    fun animateList() {
        applyAndAnimateRemovals()
        applyAndAnimateAdditions()
        applyAndAnimateMovedItems()
    }

    private fun applyAndAnimateRemovals() {
        for (i in showList.size - 1 downTo 0) {
            val model: SearchDataModel = showList[i]
            if (!filterList.contains(model)) {
                removeItem(i)
            }
        }
    }

    private fun applyAndAnimateAdditions() {
        var i = 0
        val count = filterList.size
        while (i < count) {
            val model: SearchDataModel = filterList[i]
            if (!showList.contains(model)) {
                addItem(i, model)
            }
            i++
        }
    }

    private fun applyAndAnimateMovedItems() {
        for (toPosition in filterList.indices.reversed()) {
            val model: SearchDataModel = filterList[toPosition]
            val fromPosition: Int = showList.indexOf(model)
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition)
            }
        }
    }

    private fun removeItem(position: Int) {
        showList.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun addItem(position: Int, model: SearchDataModel) {
        showList.add(0, model)
        notifyItemInserted(0)
    }

    private fun moveItem(fromPosition: Int, toPosition: Int) {
        val model: SearchDataModel = showList.removeAt(fromPosition)
        showList.add(toPosition, model)
        notifyItemMoved(fromPosition, toPosition)
    }
}
