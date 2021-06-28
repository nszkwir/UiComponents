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

    private var hour: Int
    private var minute: Int
    private var year: Int
    private var month: Int
    private var day: Int

    init {
        val rightNow = Calendar.getInstance()
        hour = rightNow.get(Calendar.HOUR_OF_DAY)
        minute = rightNow.get(Calendar.MINUTE)
        year = rightNow.get(Calendar.YEAR)
        month = rightNow.get(Calendar.MONTH)
        day = rightNow.get(Calendar.DAY_OF_MONTH)
    }

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

        onTimeSet(minute, hour)
        onDateSet(day, month, year)

        timeEditText.setOnClickListener {
            TimePickerDialogFragment(
                { pMinute, pHour -> onTimeSet(pMinute, pHour) },
                minute,
                hour
            ).show(
                parentFragmentManager,
                "timePicker"
            )
        }
        dateEditText.setOnClickListener {
            DatePickerDialogFragment(
                { pDay, pMonth, pYear -> onDateSet(pDay, pMonth, pYear) },
                day,
                month,
                year
            ).show(
                parentFragmentManager,
                "datePicker"
            )
        }
    }

    private fun onTimeSet(pMinute: Int, pHour: Int) {
        minute = pMinute
        hour = pHour
        val formattedMinute = String.format("%02d", pMinute)
        val formattedHour = String.format("%02d", pHour)
        timeEditText.text = "$formattedHour:$formattedMinute"
    }

    private fun onDateSet(pDay: Int, pMonth: Int, pYear: Int) {
        day = pDay
        month = pMonth
        year = pYear
        val formattedDay = String.format("%02d", pDay)
        val formattedMonth = String.format("%02d", (pMonth + 1))
        dateEditText.text = "$pYear-$formattedMonth-$formattedDay"
    }
}
