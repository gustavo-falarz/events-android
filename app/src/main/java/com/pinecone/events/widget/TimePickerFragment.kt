package com.pinecone.events.widget

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.format.DateFormat
import android.widget.TimePicker
import java.util.*

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    private lateinit var activity: Context
    private lateinit var listener: (Int, Int) -> Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(v: TimePicker?, hourOfDay: Int, minutes: Int) {
        listener(hourOfDay, minutes)
    }

    companion object {
        fun newInstance(activity: Context, listener: (hourOfDay: Int, minute: Int) -> Unit): TimePickerFragment {
            val fragment = TimePickerFragment()
            fragment.activity = activity
            fragment.listener = listener
            return fragment
        }
    }
}