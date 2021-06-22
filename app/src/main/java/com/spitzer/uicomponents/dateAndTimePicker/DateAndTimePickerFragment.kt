package com.spitzer.uicomponents.dateAndTimePicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.spitzer.uicomponents.databinding.FragmentTimePickerBinding
import kotlinx.android.synthetic.main.fragment_time_picker.*
import java.util.*

class DateAndTimePickerFragment : Fragment() {
    private lateinit var binding: FragmentTimePickerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimePickerBinding.inflate(
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
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        onTimeSet(minute,hour)
        onDateSet(day,month,year)

        timeEditText.setOnClickListener {
            TimePickerDialogFragment { minute, hour -> onTimeSet(minute, hour) }.show(
                parentFragmentManager,
                "timePicker"
            )
        }
        dateEditText.setOnClickListener {
            DatePickerDialogFragment { day, month, year -> onDateSet(day, month, year) }.show(
                parentFragmentManager,
                "datePicker"
            )
        }
    }

    private fun onTimeSet(minute: Int, hour: Int) {
        timeEditText.text = "$hour:$minute"
    }

    private fun onDateSet(day: Int, month: Int, year: Int) {
        dateEditText.text = "$year-$month-$day"
    }
}
