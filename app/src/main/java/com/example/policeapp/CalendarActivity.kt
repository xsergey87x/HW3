package com.example.policeapp

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

class CalendarActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val arriveYear = findViewById<EditText>(R.id.arriveYear)
        val arriveMonth = findViewById<EditText>(R.id.arriveMounth)
        val arriveDay = findViewById<EditText>(R.id.arriveDay)
        val departureYear = findViewById<EditText>(R.id.departYear)
        val departureMonth = findViewById<EditText>(R.id.departMounth)
        val departureDay = findViewById<EditText>(R.id.departDay)

        val checkBut = findViewById<Button>(R.id.checkButton)
        val resultTextList = findViewById<ListView>(R.id.resultList)

        val nameList = ArrayList<String>()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nameList)
        var remainsDay = 90L
        var periodArrive = 0L
        var periodDeparture = 0L

        val current = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        val periodNow = current.toEpochDay()

        checkBut.setOnClickListener {

            if ((arriveYear.text.toString() != "") && (arriveMonth.text.toString() != "") && (arriveDay.text.toString() != "")) {
                if ((arriveYear.text.toString().toInt() in 1970..3000) &&
                        (arriveMonth.text.toString().toInt() in 1..12) && (arriveDay.text.toString().toInt() in 1..31)) {
                    val arriveDat = LocalDate.of(arriveYear.text.toString().toInt(), arriveMonth.text.toString().toInt(), arriveDay.text.toString().toInt())
                    periodArrive = arriveDat.toEpochDay()
                }

                if ((departureYear.text.toString() != "") && (departureMonth.text.toString() != "") && (departureDay.text.toString() != "")) {
                    if ((departureYear.text.toString().toInt() in 1970..3000) && (departureMonth.text.toString().toInt() in 1..12)
                            && (departureDay.text.toString().toInt() in 1..31)) {
                        val departDat = LocalDate.of(departureYear.text.toString().toInt(), departureMonth.text.toString().toInt(), departureDay.text.toString().toInt())
                        periodDeparture = departDat.toEpochDay()
                    }

                } else {
                    periodDeparture = periodNow
                }

                if (periodArrive <= periodDeparture) {
                    if ((periodDeparture >= periodNow) && (periodArrive <= periodNow) && (periodArrive >= (periodNow - 180))) {
                        remainsDay -= (periodNow - periodArrive)
                    } else if ((periodDeparture >= periodNow) && (periodArrive < (periodNow - 180))) {
                        remainsDay -= (periodNow - (periodNow - 180))
                    } else if ((periodDeparture > (periodNow - 180)) && (periodDeparture < periodNow) && (periodArrive < (periodNow - 180))) {
                        remainsDay -= (periodDeparture - (periodNow - 180))
                    } else if ((periodDeparture > (periodNow - 180)) && (periodDeparture < periodNow) && (periodArrive >= (periodNow - 180)) && (periodArrive <= periodNow)) {
                        remainsDay -= (periodDeparture - periodArrive)
                    }


                    resultTextList.adapter = adapter
                    if (remainsDay > 0) {
                        nameList.add("You have left  $remainsDay day")
                    } else if (remainsDay < 0) {
                        nameList.add("You are late $remainsDay day")
                    }

                } else {
                    resultTextList.adapter = adapter
                    nameList.add("Arrival and departure date is not correct")
                }

            } else {
                resultTextList.adapter = adapter
                nameList.add("Data arrive is uncorrect")
            }
        }

    }
}
