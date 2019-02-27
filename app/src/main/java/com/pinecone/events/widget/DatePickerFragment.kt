package com.pinecone.events.widget

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.DatePicker
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var activity: Context
    private lateinit var listener: (Int, Int, Int) -> Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(activity, this, year, month, day)
        dialog.datePicker.minDate = c.timeInMillis
        return dialog
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        listener(year, month, day)
    }

    companion object {
        fun newInstance(activity: Context, listener: (hourOfDay: Int, minute: Int, Int) -> Unit): DatePickerFragment {
            val fragment = DatePickerFragment()
            fragment.activity = activity
            fragment.listener = listener
            return fragment
        }
    }
}