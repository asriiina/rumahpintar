package com.example.rumahpintar.langganan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rumahpintar.R
import kotlinx.android.synthetic.main.activity_detail_langganan.*

class DetailLanggananActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_QUESTION = "extra_question"
        const val EXTRA_PRICE = "extra_price"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_langganan)

        showProductDetail()

    }

    private fun showProductDetail() {
        tv_name.text = intent.getStringExtra(EXTRA_NAME)
        tv_question.text = intent.getIntExtra(EXTRA_QUESTION, 0).toString()
        tv_price.text = intent.getIntExtra(EXTRA_PRICE, 0).toString()
    }


}