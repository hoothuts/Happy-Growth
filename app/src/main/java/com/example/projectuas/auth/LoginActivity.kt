package com.example.projectuas

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuas.auth.RegisterActivity // Pastikan import ini sesuai package Anda
import com.example.projectuas.dashboard.DashboardActivity
import com.example.projectuas.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var bind: ActivityLoginBinding
    private var isPasswordVisible = false

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Show/hide password
        bind.btnShowPassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            togglePasswordVisibility()
        }

        // Login Action
        bind.btnLogin.setOnClickListener {
            loginProcess()
        }

        // ===========================================
        // 🔥 TAMBAHAN LOGIC KE REGISTER DI SINI 🔥
        // ===========================================
        bind.tvRegister.setOnClickListener {
            // Arahkan ke halaman RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            bind.inputPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            bind.inputPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        bind.inputPassword.setSelection(bind.inputPassword.text?.length ?: 0)
    }

    private fun loginProcess() {
        val email = bind.inputEmail.text.toString().trim()
        val password = bind.inputPassword.text.toString().trim()

        if (email.isEmpty()) {
            bind.inputEmail.error = "Email wajib diisi"
            return
        }

        if (password.isEmpty()) {
            bind.inputPassword.error = "Password wajib diisi"
            return
        }

        // 🔥 Login dengan Firebase Auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val uid = result.user?.uid ?: return@addOnSuccessListener

                // 🔥 Ambil data user dari Firestore
                firestore.collection("users")
                    .document(uid)
                    .get()
                    .addOnSuccessListener { doc ->
                        if (doc.exists()) {
                            val name = doc.getString("name") ?: "Pengguna"

                            Toast.makeText(
                                this,
                                "Selamat datang, $name!",
                                Toast.LENGTH_SHORT
                            ).show()

                            // Pindah ke dashboard
                            val intent = Intent(this, DashboardActivity::class.java)
                            intent.putExtra("userName", name)
                            intent.putExtra("email", email)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Data user tidak ditemukan!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Gagal mengambil data user!", Toast.LENGTH_SHORT).show()
                    }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Login gagal: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}