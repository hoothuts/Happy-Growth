package com.example.projectuas.anak

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuas.R
import com.example.projectuas.adapter.MateriAnakAdapter
import com.example.projectuas.model.MateriAnakItem

class MateriAnakActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materi_anak)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        val materiList = listOf(
            MateriAnakItem(
                title = "Mengenal Huruf A–Z",
                description = "Belajar mengenal huruf alfabet",
                imageResId = R.drawable.ic_huruf,
                content = """
🔤 MENGENAL HURUF A–Z

Anak akan belajar mengenal huruf alfabet dari A sampai Z dengan cara menyenangkan.

Tujuan:
• Mengenal bentuk huruf
• Melatih membaca awal
• Menambah kosakata

Contoh:
A untuk Ayam 🐔
B untuk Bola ⚽
C untuk Cicak 🦎

Aktivitas:
✔ Mengucapkan huruf
✔ Menunjuk huruf
✔ Menebalkan huruf
                """.trimIndent()
            ),

            MateriAnakItem(
                title = "Belajar Angka 1–10",
                description = "Pengenalan angka dasar",
                imageResId = R.drawable.ic_angka,
                content = """
🔢 BELAJAR ANGKA 1–10

Materi mengenal angka dan menghitung sederhana.

Tujuan:
• Mengenal angka
• Menghitung benda
• Logika dasar

Contoh:
1 🍎
2 🧸🧸
3 🚗🚗🚗
                """.trimIndent()
            ),

            MateriAnakItem(
                title = "Mewarnai Gambar",
                description = "Melatih motorik halus",
                imageResId = R.drawable.ic_mewarnai,
                content = """
🎨 MEWARNAI GAMBAR

Mewarnai membantu kreativitas dan motorik anak.

Manfaat:
• Koordinasi tangan
• Kreativitas
• Mengenal warna
                """.trimIndent()
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rvSchedule)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = MateriAnakAdapter(materiList) { materi ->
            val intent = Intent(this, DetailMateriAnakActivity::class.java)
            intent.putExtra("title", materi.title)
            intent.putExtra("content", materi.content)
            intent.putExtra("image", materi.imageResId)
            startActivity(intent)
        }
    }
}
