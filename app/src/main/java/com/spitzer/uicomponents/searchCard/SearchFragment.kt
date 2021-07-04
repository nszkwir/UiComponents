package com.spitzer.uicomponents.searchCard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spitzer.uicomponents.R
import com.spitzer.uicomponents.databinding.FragmentSearchBinding
import com.spitzer.uicomponents.searchCard.adapter.SearchListAnimatedAdapter
import com.spitzer.uicomponents.searchCard.adapter.SimpleDividerItemDecoration
import com.spitzer.uicomponents.searchCard.data.SearchDataHelper
import com.spitzer.uicomponents.searchCard.data.SearchDataModel
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val searchListAdapter = SearchListAnimatedAdapter(SearchDataHelper.getSampleData())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            searchListAdapter.onItemClickFunction { item -> onItemClicked(item)  }
            recyclerViewSearch.apply {
                layoutManager =
                    LinearLayoutManager(it, RecyclerView.VERTICAL, false)
                addItemDecoration(
                    SimpleDividerItemDecoration(
                        it,
                        R.drawable.line_divider
                    )
                )
                adapter = searchListAdapter
            }
        }
        searchCardBar.onClickFunction { showMessage() }
        searchCardBar.onTextChangedFunction { text, start, before, count -> onTextChanged(
            text,
            start,
            before,
            count) }
    }

    private fun showMessage() = Unit

    private fun onTextChanged(text: CharSequence?,
                              start: Int,
                              before: Int,
                              count: Int) {
        dataCardView.visibility = GONE
        searchListAdapter.filter.filter(text)
    }

    private fun onItemClicked(itemClicked: SearchDataModel) {
        dataCardView.visibility = VISIBLE
        tvIDVal.text = itemClicked.id.toString()
        tvDNIVal.text = itemClicked.dni
        tvApeNomVal.text = itemClicked.nombre + " " + itemClicked.apellido
        tvTelVal.text = itemClicked.telefono
        tvEmailVal.text = itemClicked.email
    }
}
