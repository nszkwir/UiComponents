package com.spitzer.uicomponents.keywordsearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.spitzer.uicomponents.databinding.FragmentKeywordSearchBinding
import kotlinx.android.synthetic.main.fragment_keyword_search.*

class KeywordSearchFragment : Fragment() {
    private lateinit var binding: FragmentKeywordSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKeywordSearchBinding.inflate(
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
        keywordSearch1.setOnErrorMessage { errorMessage ->
            Snackbar.make(
                requireView(),
                errorMessage,
                Snackbar.LENGTH_SHORT
            ).show()
        }
        keywordSearch1.setOnSearchFunction { query ->
            Snackbar.make(
                requireView(),
                query,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}
