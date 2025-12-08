package com.example.projectuas.dashboard

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuas.R

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val name = intent.getStringExtra("userName") ?: "User"
        val email = intent.getStringExtra("email") ?: ""

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        tvWelcome.text = "Selamat datang, $name"
    }
}