package com.example.rumahpintar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rumahpintar.home.HomeFragment
import kotlinx.android.synthetic.main.activity_sukses.*

class SuksesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sukses)

        btn_home.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}