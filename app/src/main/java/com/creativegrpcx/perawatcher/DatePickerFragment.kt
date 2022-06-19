package com.creativegrpcx.perawatcher

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.creativegrpcx.perawatcher.data.Date
import com.creativegrpcx.perawatcher.data.Time
import java.util.*

class DatePickerFragment(private val dateTimeInterface: DateTimeInterface): DialogFragment() , DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of TimePickerDialog and return it
        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        //add plus one to the month because android sdk month start with 0
        dateTimeInterface.onDateSet(view, Date( year, month + 1, dayOfMonth))
    }
}

class TimePickerFragment(private val dateTimeInterface: DateTimeInterface) : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(requireActivity(), this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        // Do something with the time chosen by the user
        dateTimeInterface.onTimeSet(view, Time (hourOfDay, minute))
    }
}

interface DateTimeInterface {
    fun onDateSet(view: DatePicker?, date : Date)
    fun onTimeSet(view: TimePicker, time: Time)
}
