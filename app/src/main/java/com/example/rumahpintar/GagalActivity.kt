package com.example.rumahpintar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rumahpintar.langganan.LanggananActivity
import kotlinx.android.synthetic.main.activity_gagal.*

class GagalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gagal)

        btn_kembali.setOnClickListener {
            startActivity(Intent(this, LanggananActivity::class.java))
            finish()
        }
    }
}