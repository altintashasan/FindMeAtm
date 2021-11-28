package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


//handling when the withdraw or deposit button is touched
class atm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atm)
        nextactivity()
    }

    private fun nextactivity() {
        val next = findViewById<View>(R.id.button) as Button
        next.setOnClickListener { startActivity(Intent(this@atm, MainActivity::class.java)) }
        val next1 = findViewById<View>(R.id.button1) as Button
        next1.setOnClickListener { startActivity(Intent(this@atm, Deposit::class.java)) }
    }
}