package com.spitzer.uicomponents.themecolor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.spitzer.uicomponents.databinding.FragmentThemeColorBinding

class ThemeColorFragment : Fragment() {
    private lateinit var binding: FragmentThemeColorBinding

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
        val viewModel = ViewModelProvider(this).get(ThemeColorViewModel::class.java)
        viewModel.redValue.observe(viewLifecycleOwner, Observer {
            updateViewBackGround()
        })
        viewModel.greenValue.observe(viewLifecycleOwner, Observer {
            updateViewBackGround()
        })
        viewModel.blueValue.observe(viewLifecycleOwner, Observer {
            updateViewBackGround()
        })
        binding.viewModel = viewModel
    }

    fun updateViewBackGround(){
        binding.colorSampler.setBackgroundColor(binding.viewModel!!.updateColor())
    }
}