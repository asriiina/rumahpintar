package com.example.rumahpintar.langganan

import android.app.*
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.rumahpintar.MainActivity
import com.example.rumahpintar.R
import com.example.rumahpintar.SuksesActivity
import kotlinx.android.synthetic.main.activity_detail_langganan.*

class DetailLanggananActivity : AppCompatActivity() {

    private var cancellationSignal: CancellationSignal? = null
    private val authenticationCallback: BiometricPrompt.AuthenticationCallback
        get() =
            @RequiresApi(Build.VERSION_CODES.P)
            object : BiometricPrompt.AuthenticationCallback() {

                val notificationManager = NotificationManagerCompat.from(this@DetailLanggananActivity)
                val intent = Intent(this@DetailLanggananActivity, SuksesActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(this@DetailLanggananActivity, 0, intent, 0)

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    notifyUser("Authentication error: $errString")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
                    notifyUser("Authentication success!")
                    val notification = NotificationCompat.Builder(this@DetailLanggananActivity, CHANNEL_ID)
                        .setContentTitle("Notifikasi Pembayaran")
                        .setContentText("Pembayaran Berhasil")
                        .setSmallIcon(R.drawable.notif_payment)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .build()
                    notificationManager.notify(NOTIFICATION_ID, notification)

                    startActivity(Intent(this@DetailLanggananActivity, SuksesActivity::class.java))
                }
            }

    companion object {
        const val CHANNEL_ID = "channelID"
        const val CHANNEL_NAME = "channelName"
        const val NOTIFICATION_ID = 0
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_QUESTION = "extra_question"
        const val EXTRA_PRICE = "extra_price"

    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_langganan)
        showProductDetail()
        createNotificationChannel()
        checkBiometricSupport()

        btn_bayar.setOnClickListener {

            val biometricPrompt = BiometricPrompt.Builder(this)
                .setTitle("Pembayaran Kelas")
                .setSubtitle("Dibutuhkan Autentikasi")
                .setDescription("Aplikasi ini menggunakan fingerprint untuk melindungi data anda")
                .setNegativeButton(
                    "Cancel",
                    this.mainExecutor,
                    DialogInterface.OnClickListener { dialogInterface, which ->
                        notifyUser("Autentikasi dibatalkan")
                    }).build()

            biometricPrompt.authenticate(
                getCancellationSignal(),
                mainExecutor,
                authenticationCallback
            )

            startActivity(Intent(this@DetailLanggananActivity, PembayaranActivity::class.java))
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun showProductDetail() {
        tv_name.text = intent.getStringExtra(EXTRA_NAME)
        tv_question.text = intent.getStringExtra(EXTRA_QUESTION)
        tv_price.text = intent.getStringExtra(EXTRA_PRICE)
    }

    private fun notifyUser(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            notifyUser("Autentikasi dibatalkan oleh user")
        }
        return cancellationSignal as CancellationSignal
    }

    private fun checkBiometricSupport(): Boolean {
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if (!keyguardManager.isKeyguardSecure) {
            notifyUser("Autentikasi fingerprint belum diaktifkan")
            return false
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.USE_BIOMETRIC
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            notifyUser("Izin autentikasi fingerprint belum diaktifkan")
            return false
        }
        return if (packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            true
        } else true
    }
}