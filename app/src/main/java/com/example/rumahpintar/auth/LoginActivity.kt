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

class LoginActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        btn_login.setOnClickListener {
            val email = txt_email.text.toString()
            val password = txt_password.text.toString()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this@LoginActivity,
                    "Isi Nomor HP & Password Anda",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else {
                if (email == "user@gmail.com" && password == "123") {

                    editor.putString("EMAIL", email)
                    editor.putString("PASSWORD", password)
                    editor.apply()

                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "No HP / Password Anda Salah!",
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