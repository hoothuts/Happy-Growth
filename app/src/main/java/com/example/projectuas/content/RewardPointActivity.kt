package com.example.projectuas.content

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuas.R

class RewardPointActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reward_point)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnShare = findViewById<ImageView>(R.id.btnShare)

        btnBack.setOnClickListener {
            finish()
        }

        btnShare.setOnClickListener {
            Toast.makeText(this, "Sharing functionality coming soon!", Toast.LENGTH_SHORT).show()
        }
    }
}
