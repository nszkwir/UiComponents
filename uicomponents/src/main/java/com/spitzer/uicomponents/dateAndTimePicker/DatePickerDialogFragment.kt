package com.spitzer.uicomponents.dateAndTimePicker

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment

class DatePickerDialogFragment(
    val linkingFunction: (Int, Int, Int) -> Unit,
    private val day: Int,
    private val month: Int,
    private val year: Int
) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        linkingFunction(dayOfMonth, month, year)
    }
}
