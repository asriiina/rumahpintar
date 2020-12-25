package com.example.rumahpintar.langganan

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.rumahpintar.MainActivity
import com.example.rumahpintar.R
import com.example.rumahpintar.SuksesActivity
import kotlinx.android.synthetic.main.activity_detail_langganan.*

class DetailLanggananActivity : AppCompatActivity() {

    companion object {
        const val CHANNEL_ID = "channelID"
        const val CHANNEL_NAME = "channelName"
        const val NOTIFICATION_ID = 0
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_QUESTION = "extra_question"
        const val EXTRA_PRICE = "extra_price"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_langganan)
        showProductDetail()
        createNotificationChannel()

        val notificationManager = NotificationManagerCompat.from(this)
        val intent = Intent(this, SuksesActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent,0)

        btn_bayar.setOnClickListener {
            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Notifikasi Pembayaran")
                .setContentText("Pembayaran Berhasil")
                .setSmallIcon(R.drawable.notif_payment)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()

            notificationManager.notify(NOTIFICATION_ID, notification)

            startActivity(Intent(this, PembayaranActivity::class.java))
        }
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun showProductDetail() {
        tv_name.text = intent.getStringExtra(EXTRA_NAME)
        tv_question.text = intent.getStringExtra(EXTRA_QUESTION)
        tv_price.text = intent.getStringExtra(EXTRA_PRICE)
    }


}