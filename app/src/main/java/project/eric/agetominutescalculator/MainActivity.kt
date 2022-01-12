package project.eric.agetominutescalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var btnSelectDate : Button

    private lateinit var tvSelectedDate : TextView

    private lateinit var tvAgeInMinutes : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSelectDate = findViewById(R.id.btnSelectDate)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btnSelectDate.setOnClickListener { view ->
            clickDatePicker(view)
        }

    }

    private fun clickDatePicker(view: View) {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                val selectedDate = "${month + 1}/$day/$year"
                tvSelectedDate.text = selectedDate

                val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)

                val date = sdf.parse(selectedDate)
                val selectedDateToMinutes = date!!.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentDate!!.time / 60000

                val difference = currentDateToMinutes - selectedDateToMinutes

                tvAgeInMinutes.text = "" + difference
        }, year, month, day)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}