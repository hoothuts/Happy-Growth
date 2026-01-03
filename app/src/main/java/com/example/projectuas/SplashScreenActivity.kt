package com.example.projectuas

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuas.dashboard.DashboardActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Sembunyikan ActionBar jika ada, agar full screen
        supportActionBar?.hide()

        // Handler untuk menunda selama 3 detik (3000ms)
        Handler(Looper.getMainLooper()).postDelayed({
            checkLoginStatus()
        }, 3000)
    }

    private fun checkLoginStatus() {
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        if (currentUser != null) {
            // User sudah login -> Ke Dashboard
            // Kita kirim uid/name jika perlu, tapi Dashboard sudah punya logic fetch sendiri
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        } else {
            // User belum login -> Ke Login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Tutup Splash Screen agar user tidak bisa back ke sini
        finish()
    }
}