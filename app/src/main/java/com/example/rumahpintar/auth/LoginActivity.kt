package com.example.rumahpintar.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rumahpintar.MainActivity
import com.example.rumahpintar.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }

        tv_register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }
    }
}