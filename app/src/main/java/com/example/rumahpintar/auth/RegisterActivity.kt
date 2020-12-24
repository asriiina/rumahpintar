package com.example.rumahpintar.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rumahpintar.MainActivity
import com.example.rumahpintar.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_register.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
            finish()
        }

        tv_login.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        }
    }
}