package com.spitzer.uicomponents.searchCard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.spitzer.uicomponents.R
import com.spitzer.uicomponents.databinding.FragmentSearchCardBinding
import kotlinx.android.synthetic.main.fragment_search_card.*

class SearchCardFragment : Fragment() {
    private lateinit var binding: FragmentSearchCardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchCardBinding.inflate(
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
        searchCard1.onClickFunction { showMessage("1") }
        searchCard2.onClickFunction { showMessage("2") }
        searchCard3.onClickFunction { showMessage("3") }
        searchCard4.onClickFunction { showMessage("4") }
        searchCard5.onClickFunction { showMessage("5") }
        searchCard6.onClickFunction { showMessage("6") }
        searchCard7.onClickFunction { showMessage("7") }
        searchCard8.onClickFunction { showMessage("8") }
    }

    private fun showMessage(message: String) {
        textViewSearchCardSelected.text = "SearchCard clicked: $message"
        parentFragmentManager.commit {
            replace(R.id.fragment_container_view, SearchFragment())
            setReorderingAllowed(false)
            addToBackStack(null)
        }
    }
}
