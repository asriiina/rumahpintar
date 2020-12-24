package com.example.rumahpintar.langganan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.rumahpintar.R
import kotlinx.android.synthetic.main.activity_detail_langganan.*

class DetailLanggananActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_langganan)
        val timer = MyCounter(31000, 1000)
        timer.start()
    }

    inner class MyCounter(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

        override fun onFinish() {
            println("Timer Completed.")
            count.text = "Timer Completed."
        }

        override fun onTick(millisUntilFinished: Long) {
            count.textSize = 50f

            count.text = (millisUntilFinished / 1000).toString() + ""
            println("Timer  : " + millisUntilFinished / 1000)
        }
    }


}