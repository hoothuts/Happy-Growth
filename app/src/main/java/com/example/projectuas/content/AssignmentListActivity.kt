package com.example.projectuas.content

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuas.R

class AssignmentListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment_list)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        // Setup listeners for "Mulai Kerjakan" buttons
        val btnStart1 = findViewById<Button>(R.id.btnStart1)
        val btnStart2 = findViewById<Button>(R.id.btnStart2)
        val btnStart3 = findViewById<Button>(R.id.btnStart3)

        val startQuizListener = {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }

        btnStart1.setOnClickListener { startQuizListener() }
        btnStart2.setOnClickListener { startQuizListener() }
        btnStart3.setOnClickListener { startQuizListener() }
    }
}
