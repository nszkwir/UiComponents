package com.spitzer.uicomponents.genderPicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.spitzer.uicomponents.databinding.FragmentGenderPickerBinding
import kotlinx.android.synthetic.main.fragment_gender_picker.*

class GenderPickerFragment : Fragment() {
    private lateinit var binding: FragmentGenderPickerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGenderPickerBinding.inflate(
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

        genderPicker1.onValuesChanges { selectedGender: Int -> getSelectedGender(selectedGender)   }

        numberSelector1.onValuesChanges{ selectedNumber: Int -> getSelectedNumber(selectedNumber) }
        numberSelector2.onValuesChanges{ selectedNumber: Int -> getSelectedNumber(selectedNumber) }
        numberSelector3.onValuesChanges{ selectedNumber: Int -> getSelectedNumber(selectedNumber) }
        numberSelector4.onValuesChanges{ selectedNumber: Int -> getSelectedNumber(selectedNumber) }
        numberSelectorX.onValuesChanges{ selectedNumber: Int -> getSelectedNumber(selectedNumber) }
        defaultNumberSelector.onValuesChanges{ selectedNumber: Int -> getSelectedNumber(selectedNumber) }
    }

    private fun getSelectedNumber(selectedNumber: Int) {
        numberSelectedText.text = selectedNumber.toString()
    }

    private fun getSelectedGender(selectedGender: Int) {
        numberSelectedText.text = selectedGender.toString()
    }
}