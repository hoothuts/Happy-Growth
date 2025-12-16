package com.example.projectuas.profile

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuas.R

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnDone = findViewById<TextView>(R.id.btnDone)

        btnBack.setOnClickListener {
            finish()
        }

        btnDone.setOnClickListener {
            finish()
        }
    }
}
