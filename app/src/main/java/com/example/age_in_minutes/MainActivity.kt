package com.example.age_in_minutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnDate: Button = findViewById(R.id.btn)
        btnDate.setOnClickListener{view-> clickDatePicker(view)}

    }
    private fun clickDatePicker(view: View){
        val myCalendar =Calendar.getInstance();
        val year =myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day  =myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
                view,selectedYear,selectedMonth,selectedDayOfMonth ->
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            var tvSelectedDate: TextView =findViewById(R.id.tvSelectedDate)
            tvSelectedDate.text = selectedDate
            val sdf =SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            //TODO check documentation

            val theDate =sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate!!.time / 60000
            val currentDate= sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToMinutes = currentDate!!.time / 60000
            val differenceInMinutes = currentDateToMinutes-selectedDateInMinutes
            var tvAgeInMinutes: TextView = findViewById(R.id.tvSelectedDateInMinutes)
            tvAgeInMinutes.text=differenceInMinutes.toString()
        },year,month,day)
        dpd.datePicker.setMaxDate(Date().time- 86400000)
        dpd.show()
    }
}