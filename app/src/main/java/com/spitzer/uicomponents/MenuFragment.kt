package com.spitzer.uicomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.spitzer.uicomponents.animatedButton.AnimatedButtonFragment
import com.spitzer.uicomponents.databinding.FragmentMenuBinding
import com.spitzer.uicomponents.genderPicker.GenderPickerFragment
import com.spitzer.uicomponents.numberSelector.NumberSelectorFragment
import com.spitzer.uicomponents.themecolor.ThemeColorFragment

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(
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

        binding.themeEditorButton.setOnClickListener {
            navigateToFragment(THEME_EDITOR_ACCESS)
        }
        binding.numberSelectorButton.setOnClickListener {
            navigateToFragment(NUMBER_SELECTOR_ACCESS)
        }
        binding.animatedButtonButton.setOnClickListener {
            navigateToFragment(ANIMATED_BUTTON_ACCESS)
        }
        binding.genderPickerButton.setOnClickListener {
            navigateToFragment(GENDER_PICKER_ACCESS)
        }
    }

    private fun navigateToFragment(fragmentStringAccess: String) {
        val fragment: Fragment = when (fragmentStringAccess) {
            THEME_EDITOR_ACCESS -> ThemeColorFragment()
            NUMBER_SELECTOR_ACCESS -> NumberSelectorFragment()
            ANIMATED_BUTTON_ACCESS -> AnimatedButtonFragment()
            GENDER_PICKER_ACCESS -> GenderPickerFragment()
            else -> Fragment()
        }
        parentFragmentManager.commit {
            replace(R.id.fragment_container_view, fragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    companion object {
        const val THEME_EDITOR_ACCESS = "THEME_EDITOR_ACCESS"
        const val NUMBER_SELECTOR_ACCESS = "NUMBER_SELECTOR_ACCESS"
        const val ANIMATED_BUTTON_ACCESS = "ANIMATED_BUTTON_ACCESS"
        const val GENDER_PICKER_ACCESS = "GENDER_PICKER_ACCESS"
    }
}
