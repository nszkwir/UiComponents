package com.spitzer.uicomponents.numberSelector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.spitzer.uicomponents.databinding.FragmentNumberSelectorBinding
import kotlinx.android.synthetic.main.fragment_number_selector.*

class NumberSelectorFragment : Fragment() {
    private lateinit var binding: FragmentNumberSelectorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNumberSelectorBinding.inflate(
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

        numberSelector1.onValuesChanges{ selectedNumber: Int -> getSelectedNumber(selectedNumber) }
        numberSelector2.onValuesChanges{ selectedNumber: Int -> getSelectedNumber(selectedNumber) }
        defaultNumberSelector.onValuesChanges{ selectedNumber: Int -> getSelectedNumber(selectedNumber) }
    }

    private fun getSelectedNumber(selectedNumber: Int) {
        numberSelectedText.text = selectedNumber.toString()
    }
}
