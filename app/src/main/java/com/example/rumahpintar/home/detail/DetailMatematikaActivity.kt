package com.example.rumahpintar.home.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rumahpintar.R
import kotlinx.android.synthetic.main.activity_detail_matematika.*

class DetailMatematikaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_matematika)

        penjumlahan.setOnClickListener {
            startActivity(Intent(this@DetailMatematikaActivity, PenjumlahanActivity::class.java))
            finish()
        }

    }
}