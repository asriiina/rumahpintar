package com.example.rumahpintar.langganan

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.example.rumahpintar.R
import com.example.rumahpintar.langganan.DetailLanggananActivity.*
import kotlinx.android.synthetic.main.activity_detail_langganan.*
import kotlinx.android.synthetic.main.activity_pembayaran.*

class PembayaranActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)
 //       val timer = MyCounter(31000, 1000)
 //       timer.start()
    }

    override fun onStart() {
        super.onStart()
        for (i in 30 downTo 1) {
            Thread.sleep(1000)
            findViewById<TextView>(R.id.count).setText(i.toString())
        }
    }

   // inner class MyCounter(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {


     //   override fun onFinish() {
    //        println("Waktu Habis")
      //      count.text = "Waktu Habis"
       // }

       // override fun onTick(millisUntilFinished: Long) {
         //   count.textSize = 50f

           // count.text = (millisUntilFinished / 1000).toString() + ""
          //  println("Timer  : " + millisUntilFinished / 1000)
//        }
  //  }


}