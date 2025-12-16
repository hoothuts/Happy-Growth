package com.example.projectuas.content

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuas.R

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        val tvSkip = findViewById<TextView>(R.id.tvSkip)
        tvSkip.setOnClickListener {
            Toast.makeText(this, "Question Skipped", Toast.LENGTH_SHORT).show()
        }

        val rvQuestions = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rvQuestions)
        rvQuestions.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        val questions = listOf(
            Question(1, "Ada 5 bola di keranjang. Lalu ditambah 2 bola lagi. Berapa jumlah bola semuanya?", listOf("A. 20", "B. 7", "C. 10", "D. 8"), 1),
            Question(2, "Budi punya 3 apel, dikasih Ibu 2 apel. Berapa apel Budi sekarang?", listOf("A. 5", "B. 4", "C. 6", "D. 3"), 0),
            Question(3, "Angka setelah 9 adalah ...", listOf("A. 8", "B. 11", "C. 10", "D. 7"), 2),
            Question(4, "Huruf awal dari kata 'BUKU' adalah ...", listOf("A. U", "B. K", "C. B", "D. A"), 2),
            Question(5, "Ibu membeli 10 telur, pecah 3. Sisa telur ibu ada ...", listOf("A. 7", "B. 6", "C. 8", "D. 5"), 0),
            Question(6, "Binatang yang berkokok di pagi hari adalah ...", listOf("A. Kucing", "B. Ayam", "C. Bebek", "D. Sapi"), 1),
            Question(7, "Warna bendera Indonesia adalah ...", listOf("A. Merah Putih", "B. Biru Putih", "C. Hijau Kuning", "D. Merah Hitam"), 0),
            Question(8, "2 + 2 = ...", listOf("A. 2", "B. 3", "C. 4", "D. 5"), 2),
            Question(9, "Kaki sapi ada berapa?", listOf("A. 2", "B. 4", "C. 6", "D. 8"), 1),
            Question(10, "Matahari terbit di sebelah ...", listOf("A. Barat", "B. Selatan", "C. Utara", "D. Timur"), 3)
        )

        val adapter = QuizAdapter(questions)
        rvQuestions.adapter = adapter
    }
}
