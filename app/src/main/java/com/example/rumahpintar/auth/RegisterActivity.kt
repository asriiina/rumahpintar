package com.example.rumahpintar.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rumahpintar.MainActivity
import com.example.rumahpintar.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        btn_register.setOnClickListener {
            val name = txt_name.text.toString()
            val email = txt_email.text.toString()
            val password = txt_password.text.toString()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()

            if (name.isEmpty()) {
                txt_name.requestFocus()
                txt_name.error = "Masukkan Nama Anda"
                return@setOnClickListener
            }
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
                editor.putString("NAME", name)
                editor.putString("EMAIL", email)
                editor.putString("PASSWORD", password)
                editor.apply()

                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()
            }

        }

        tv_login.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        }
    }
}