package com.example.projectuas.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuas.LoginActivity
import com.example.projectuas.databinding.ActivityDashboardBinding
import com.example.projectuas.ui.parenting.ParentingActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi ViewBinding
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi Firebase
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Cek apakah user sedang login
        val currentUser = auth.currentUser
        if (currentUser == null) {
            // Jika tidak ada sesi login, kembalikan ke halaman login
            goToLogin()
            return
        }

        setupUserInfo(currentUser.uid)
        setupMenuActions()
    }

    private fun setupUserInfo(uid: String) {
        // 1. Coba ambil data yang dikirim dari LoginActivity (Lebih Cepat)
        val nameFromIntent = intent.getStringExtra("userName")

        if (nameFromIntent != null) {
            binding.tvGreeting.text = "Hi $nameFromIntent"
        } else {
            // 2. Jika data Intent kosong (misal aplikasi dibuka ulang), ambil dari Firestore
            fetchUserDataFromFirestore(uid)
        }

        // Catatan: Data "Kelas" dan "Anak ke-" belum ada di RegisterActivity/Firestore.
        // Untuk sementara kita biarkan hardcoded atau default.
        binding.tvSubtitle.text = "Class TK-B | Anak ke-1"

        // Setup klik pada foto profil
        binding.imgProfile.setOnClickListener {
            Toast.makeText(this, "Profil User", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchUserDataFromFirestore(uid: String) {
        firestore.collection("users").document(uid).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val name = document.getString("name") ?: "User"
                    binding.tvGreeting.text = "Hi $name"
                }
            }
            .addOnFailureListener {
                binding.tvGreeting.text = "Hi User"
                Toast.makeText(this, "Gagal memuat profil", Toast.LENGTH_SHORT).show()
            }
    }

    private fun setupMenuActions() {
        // --- Bagian Menu (Logic sama seperti sebelumnya) ---

        binding.cardMateriParenting.setOnClickListener {
            val intent = Intent(this, ParentingActivity::class.java)
            startActivity(intent)
        }

        binding.cardMateriAnak.setOnClickListener {
            Toast.makeText(this, "Menu Materi Anak", Toast.LENGTH_SHORT).show()
        }

        binding.cardTugas.setOnClickListener {
            Toast.makeText(this, "Menu Tugas", Toast.LENGTH_SHORT).show()
        }

        binding.cardReward.setOnClickListener {
            Toast.makeText(this, "Menu Reward Point", Toast.LENGTH_SHORT).show()
        }

        binding.cardMaps.setOnClickListener {
            Toast.makeText(this, "Maps Psikolog", Toast.LENGTH_SHORT).show()
        }

        // --- LOGOUT ACTION ---
        binding.cardKeluar.setOnClickListener {
            showLogoutConfirmation()
        }
    }

    private fun showLogoutConfirmation() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi Keluar")
        builder.setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
        builder.setPositiveButton("Ya") { _, _ ->
            logout()
        }
        builder.setNegativeButton("Batal") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun logout() {
        // 1. Sign out dari Firebase Auth
        auth.signOut()

        // 2. Kembali ke halaman Login
        goToLogin()
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        // Hapus stack activity agar user tidak bisa back ke dashboard setelah logout
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}