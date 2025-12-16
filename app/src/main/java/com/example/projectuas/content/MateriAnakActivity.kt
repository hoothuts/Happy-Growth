package com.example.projectuas.content

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuas.R

class MateriAnakActivity : AppCompatActivity() {

    private lateinit var adapter: ScheduleAdapter
    private val scheduleMap = mapOf(
        "SEN" to listOf(
            ScheduleItem("Calistung Dasar", "08:15am - 9:00am", "Bambang Sutedjo", "Period 1"),
            ScheduleItem("Belajar Huruf & Kata Sederhana", "09:00am - 09:45am", "Sari Ningsih", "Period 2"),
            ScheduleItem("Lunch Break", "10:30am - 11:00am", "", "", "🥪"),
            ScheduleItem("Membaca Cerita Pendek", "09:45am - 10:30am", "Marta Marina", "Period 3")
        ),
        "SEL" to listOf(
            ScheduleItem("Matematika Pra-Dasar", "08:15am - 9:00am", "Bambang Sutedjo", "Period 1"),
            ScheduleItem("Mengenal Angka dan Pola", "09:00am - 09:45am", "Joko Santoso", "Period 2"),
             ScheduleItem("Lunch Break", "10:30am - 11:00am", "", "", "🥪"),
            ScheduleItem("Seni dan Kerajinan", "09:45am - 10:30am", "Endang Susilawati", "Period 3")
        ),
        "RAB" to listOf(
            ScheduleItem("Olahraga Ringan", "08:15am - 9:00am", "Pak Tono", "Period 1"),
            ScheduleItem("Menyanyi Lagu Anak", "09:00am - 09:45am", "Bu Rini", "Period 2"),
             ScheduleItem("Lunch Break", "10:30am - 11:00am", "", "", "🥪"),
             ScheduleItem("Bermain Peran", "09:45am - 10:30am", "Bu Rini", "Period 3")
        ),
        "KAM" to listOf(
            ScheduleItem("Pengenalan Alam", "08:15am - 9:00am", "Pak Alam", "Period 1"),
             ScheduleItem("Lunch Break", "10:30am - 11:00am", "", "", "🥪"),
            ScheduleItem("Eksperimen Sains Sederhana", "10:30am - 11:15am", "Pak Alam", "Period 2")
        ),
        "JUM" to listOf(
            ScheduleItem("Agama dan Budi Pekerti", "08:15am - 9:00am", "Ust. Abdullah", "Period 1"),
            ScheduleItem("Makan Bersama & Adab", "09:00am - 10:00am", "Ust. Abdullah", "Period 2")
        ),
        "SAB" to listOf(
            ScheduleItem("Ekstrakurikuler: Tari", "08:00am - 10:00am", "Bu Dewi", "Ekskul"),
            ScheduleItem("Ekstrakurikuler: Menggambar", "10:00am - 12:00pm", "Pak Raden", "Ekskul")
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materi_anak)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener { finish() }

        val rvSchedule = findViewById<RecyclerView>(R.id.rvSchedule)
        rvSchedule.layoutManager = LinearLayoutManager(this)
        
        // Initialize with Monday (SEN)
        adapter = ScheduleAdapter(scheduleMap["SEN"] ?: emptyList())
        rvSchedule.adapter = adapter

        // Setup textviews
        val dayViews = listOf(
            findViewById<TextView>(R.id.tvDay1),
            findViewById<TextView>(R.id.tvDay2),
            findViewById<TextView>(R.id.tvDay3),
            findViewById<TextView>(R.id.tvDay4),
            findViewById<TextView>(R.id.tvDay5),
            findViewById<TextView>(R.id.tvDay6)
        )
        
        val days = listOf("SEN", "SEL", "RAB", "KAM", "JUM", "SAB")
        
        // Select SEN initially
        updateSelectedDay(dayViews, 0)

        // Add listeners
        dayViews.forEachIndexed { index, textView ->
            textView.setOnClickListener {
                updateSelectedDay(dayViews, index)
                val dayKey = days[index]
                adapter.updateData(scheduleMap[dayKey] ?: emptyList())
            }
        }
    }

    private fun updateSelectedDay(views: List<TextView>, selectedIndex: Int) {
        views.forEachIndexed { index, view ->
            if (index == selectedIndex) {
                view.background = getDrawable(R.drawable.bg_pill_white)
                view.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#5B86E5"))
                view.setTextColor(Color.WHITE)
            } else {
                view.background = null
                view.backgroundTintList = null
                view.setTextColor(Color.parseColor("#333333"))
            }
        }
    }
}
