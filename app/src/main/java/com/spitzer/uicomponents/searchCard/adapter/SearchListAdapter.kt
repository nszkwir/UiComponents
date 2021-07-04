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

class SearchListAdapter(val searchDataList: ArrayList<SearchDataModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var filterList = ArrayList<SearchDataModel>()
    lateinit var onItemClick: (SearchDataModel) -> Unit

    private lateinit var mRecyclerView: RecyclerView

    init {
        filterList = searchDataList
    }

    fun onItemClickFunction(itemClickFunction: (SearchDataModel) -> Unit) {
        onItemClick = itemClickFunction
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(filterList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rowitem_search, parent, false)
        return ViewHolder(view).listenToClick { position, _ -> onItemClick(filterList[position]) }
    }

    override fun getItemCount(): Int {
        return filterList.size
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
                notifyDataSetChanged()
            }
        }
    }

}
