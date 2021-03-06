package com.spitzer.uicomponents.themecolor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.spitzer.uicomponents.colorSelector.ColorSelector
import com.spitzer.uicomponents.colorSelector.ColorSelectorViewPagerAdapter
import com.spitzer.uicomponents.databinding.FragmentThemeColorBinding
import kotlinx.android.synthetic.main.fragment_theme_color.*

class ThemeColorFragment : Fragment() {
    private lateinit var binding: FragmentThemeColorBinding
    private lateinit var viewPagerAdapter: ColorSelectorViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThemeColorBinding.inflate(
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

        val arrayOfColorSelectors = arrayListOf(
            ColorSelector(requireContext()),
            ColorSelector(requireContext()),
            ColorSelector(requireContext())
        )

        arrayOfColorSelectors.apply {
            get(0).setTitle("Primary Color")
            get(1).setTitle("Secondary Color")
            get(2).setTitle("Background Color")

            get(0).onValuesChanges { color: Int -> obtainPrimaryColor(color) }
            get(1).onValuesChanges { color: Int -> obtainSecondaryColor(color) }
            get(2).onValuesChanges { color: Int -> obtainBackgroundColor(color) }
        }

        viewPagerAdapter = ColorSelectorViewPagerAdapter(arrayOfColorSelectors)
        vPager.adapter = viewPagerAdapter
    }

    private fun obtainPrimaryColor(color: Int) {
        Toast.makeText(context, "Primary color: $color", Toast.LENGTH_SHORT).show()
    }

    private fun obtainSecondaryColor(color: Int) {
        Toast.makeText(context, "Secondary color: $color", Toast.LENGTH_SHORT).show()
    }

    private fun obtainBackgroundColor(color: Int) {
        Toast.makeText(context, "Background color: $color", Toast.LENGTH_SHORT).show()
    }
}
