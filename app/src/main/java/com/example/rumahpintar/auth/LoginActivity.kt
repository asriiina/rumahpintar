package com.example.rumahpintar.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rumahpintar.MainActivity
import com.example.rumahpintar.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.txt_email
import kotlinx.android.synthetic.main.activity_login.txt_password
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        btn_login.setOnClickListener {
            val email = txt_email.text.toString()
            val password = txt_password.text.toString()
            val getEmail = sharedPreferences.getString("EMAIL", "")
            val getPass = sharedPreferences.getString("PASSWORD", "")

            if (email.isEmpty()){
                txt_email.requestFocus()
                txt_email.error = "Masukkan Email Anda"
                return@setOnClickListener
            }
            if (password.isEmpty()){
                txt_password.requestFocus()
                txt_password.error = "Masukkan Password Anda"
                return@setOnClickListener
            } else {
                if (email == getEmail && password == getPass) {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Email / Password Anda Salah!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
            }
            finish()


        }

        tv_register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }
    }
}