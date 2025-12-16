package com.example.projectuas.profile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuas.R
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        auth = FirebaseAuth.getInstance()

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val etOldPassword = findViewById<EditText>(R.id.etOldPassword)
        val etNewPassword = findViewById<EditText>(R.id.etNewPassword)
        val etRetypePassword = findViewById<EditText>(R.id.etRetypePassword)
        val btnChangePassword = findViewById<Button>(R.id.btnChangePassword)

        btnBack.setOnClickListener {
            finish()
        }

        btnChangePassword.setOnClickListener {
            val oldPass = etOldPassword.text.toString()
            val newPass = etNewPassword.text.toString()
            val retypePass = etRetypePassword.text.toString()

            if (oldPass.isEmpty() || newPass.isEmpty() || retypePass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newPass != retypePass) {
                Toast.makeText(this, "New passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newPass.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = auth.currentUser
            if (user != null && user.email != null) {
                // Re-authenticate user
                val credential = EmailAuthProvider.getCredential(user.email!!, oldPass)
                
                user.reauthenticate(credential)
                    .addOnCompleteListener { authTask ->
                        if (authTask.isSuccessful) {
                            // Update password
                            user.updatePassword(newPass)
                                .addOnCompleteListener { updateTask ->
                                    if (updateTask.isSuccessful) {
                                        Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()
                                        finish()
                                    } else {
                                        Toast.makeText(this, "Failed to update password: ${updateTask.exception?.message}", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        } else {
                            Toast.makeText(this, "Old password incorrect", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
