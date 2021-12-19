package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { view ->
            getDatePicker(view)
        }
    }

    private fun getDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dp = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, chosenYear, chosenMonth, chosenDayOfMonth ->
            calculateAge(chosenYear, chosenMonth, chosenDayOfMonth)
        },
            year,
            month,
            day
        )
        /* For getting until previous date Date().time - 86400000 */
        dp.datePicker.maxDate = Date().time
        dp.show()
    }

    private fun calculateAge(year: Int, month: Int, day: Int) {
        val chosenDate = "$day/${month+1}/$year"
        tvSelectedDate.text = chosenDate

        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val result = sdf.parse(chosenDate)
        val chosenDateInMinutes = result!!.time / 60000

        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
        val currentDateInMinutes = currentDate!!.time / 60000

        val differenceInMinutes = currentDateInMinutes - chosenDateInMinutes
        val noOfDays = (differenceInMinutes / 60 ) / 24
        tvAgeInMinutes.text = differenceInMinutes.toString()
        tvAgeInDays.text = noOfDays.toString()
    }
}