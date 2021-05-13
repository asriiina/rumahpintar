package com.example.rumahpintar.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.rumahpintar.MainActivity
import com.example.rumahpintar.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.txt_email
import kotlinx.android.synthetic.main.activity_login.txt_password
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)

            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)

                    Toast.makeText(
                        this@LoginActivity,
                        "Email / Password Anda Salah!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        btn_login.setOnClickListener {
            val email = txt_email.text.toString()
            val password = txt_password.text.toString()
            if (email.isEmpty()) {
                txt_email.requestFocus()
                txt_email.error = "Masukkan Email Anda"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                txt_password.requestFocus()
                txt_password.error = "Masukkan Password Anda"
                return@setOnClickListener
            } else {
                signIn(email, password)
            }
        }
        tv_register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }
    }

    companion object {
        private const val TAG = "EmailPassword"
    }

}