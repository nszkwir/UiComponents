package com.spitzer.uicomponents.dateAndTimePicker

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment

class TimePickerDialogFragment(
    val linkingFunction: (Int, Int) -> Unit,
    private val minute: Int,
    private val hour: Int
) : DialogFragment(),
    TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return TimePickerDialog(
            activity,
            this,
            hour,
            minute,
            DateFormat.is24HourFormat(activity)
        )
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        linkingFunction(minute, hourOfDay)
    }
}
