package com.example.rumahpintar.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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



            startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
            finish()
        }

        tv_login.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        }
    }
}