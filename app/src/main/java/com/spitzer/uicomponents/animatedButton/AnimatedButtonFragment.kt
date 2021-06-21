package com.spitzer.uicomponents.animatedButton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.spitzer.uicomponents.databinding.FragmentAnimatedButtonBinding
import kotlinx.android.synthetic.main.fragment_animated_button.*
import kotlinx.coroutines.*

class AnimatedButtonFragment : Fragment() {
    private lateinit var binding: FragmentAnimatedButtonBinding
    private val scope = CoroutineScope(Dispatchers.Default + Job())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimatedButtonBinding.inflate(
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

        animatedButton1.apply {
            setOnButtonPressedFunction { onButtonPressedContinueSuccess() }
            setOnAnimationEndFunction { onAnimationEnd() }
        }
        animatedButton2.apply {
            setOnButtonPressedFunction { onButtonPressedContinueError() }
            setOnAnimationEndFunction { onAnimationEnd() }
        }

    }

    private fun onButtonPressedContinueSuccess() {

        Toast.makeText(requireContext(), "onButtonPressedContinueSuccess", Toast.LENGTH_SHORT)
            .show()
        scope.launch {
            try {
                Thread.sleep(1000)
                withContext(Dispatchers.Main) {
                    animatedButton1.continueSuccessAnimation()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    animatedButton1.continueSuccessAnimation()
                }
            }
        }
    }

    private fun onButtonPressedContinueError() {
        Toast.makeText(requireContext(), "onButtonPressedContinueError", Toast.LENGTH_SHORT).show()
        animatedButton2.continueErrorAnimation()
    }

    private fun onAnimationEnd() {
        Toast.makeText(requireContext(), "onAnimationEnd", Toast.LENGTH_SHORT).show()
    }
}
