package com.example.projectuas.anak

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuas.R

class DetailMateriAnakActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_materi_anak)

        findViewById<ImageView>(R.id.imgMateri)
            .setImageResource(intent.getIntExtra("image", 0))

        findViewById<TextView>(R.id.tvTitle).text =
            intent.getStringExtra("title")

        findViewById<TextView>(R.id.tvContent).text =
            intent.getStringExtra("content")
    }
}