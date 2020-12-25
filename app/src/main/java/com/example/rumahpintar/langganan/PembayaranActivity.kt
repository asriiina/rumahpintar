package com.example.rumahpintar.langganan

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rumahpintar.GagalActivity
import com.example.rumahpintar.R
import com.example.rumahpintar.SuksesActivity
import java.util.concurrent.ThreadLocalRandom

class PembayaranActivity : AppCompatActivity() {

    private var text: TextView? = null
    private var bgthread: Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)
        //       val timer = MyCounter(31000, 1000)
        //       timer.start()
    }

    override fun onStart() {
        super.onStart()
        if (bgthread == null || bgthread!!.getState() == Thread.State.TERMINATED) {
            val runnable = Runnable {
                try {
                    for(i in 30 downTo 0){
                        Thread.sleep(1000)
                        findViewById<TextView> (R.id.count).setText(i.toString())
                        if (i == 0){
                            startActivity(Intent(this, GagalActivity::class.java))
                            finish()
                        }
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            bgthread = Thread(runnable)
            bgthread!!.start()
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
}