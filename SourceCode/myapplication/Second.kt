package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


//handling when findAtm Button is touched
class Second : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        nextactivity()
    }

    private fun nextactivity() {
        val next = findViewById<View>(R.id.buttonatm) as Button
        next.setOnClickListener { startActivity(Intent(this@Second, atm::class.java)) }
    }
}