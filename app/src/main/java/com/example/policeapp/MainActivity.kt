package com.example.policeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calendarView = findViewById<View>(R.id.calendarView)
        val calculatorView = findViewById<View>(R.id.calculatorView)
        val emailView = findViewById<View>(R.id.emailView)
        val handbookView = findViewById<View>(R.id.handbookView)
        val penaltiesView = findViewById<View>(R.id.penaltiesView)
        val purchasesView = findViewById<View>(R.id.purchasesView)

        calendarView.setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
        }
        calculatorView.setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
        }
        emailView.setOnClickListener {
            startActivity(Intent(this, EmailActivity::class.java))
        }
        handbookView.setOnClickListener {
            startActivity(Intent(this, HandbookActivity::class.java))
        }
        penaltiesView.setOnClickListener {
            startActivity(Intent(this, PenaltiesActivity::class.java))
        }
        purchasesView.setOnClickListener {
            startActivity(Intent(this, PurchasesActivity::class.java))
        }
    }
}