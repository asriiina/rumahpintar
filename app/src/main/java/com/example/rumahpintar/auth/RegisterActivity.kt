package com.example.rumahpintar.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rumahpintar.R
import com.example.rumahpintar.model.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    private fun createAccount(email: String, password: String, namaUser: String) {
        auth.createUserWithEmailAndPassword(email, password)

            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    saveUser(user, namaUser, email)
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    finish()
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)

                    Toast.makeText(
                        baseContext, "Authentication failed.",

                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun saveUser(user: FirebaseUser?, namaUser: String, emailUser: String) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database!!.getReference().child("user").child(user?.uid!!)
        val userProfile = UserProfile(user?.uid!!, namaUser, emailUser)
        myRef!!.setValue(userProfile)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth
        btn_register.setOnClickListener {
            val name = txt_name.text.toString()
            val email = txt_email.text.toString()
            val password = txt_password.text.toString()
            if (name.isEmpty()) {
                txt_name.requestFocus()
                txt_name.error = "Masukkan Nama Anda"
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                txt_email.requestFocus()
                txt_email.error = "Masukkan Email Anda"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                txt_password.requestFocus()
                txt_password.error = "Masukkan Password Anda"
                return@setOnClickListener
            } else {
                createAccount(email, password, name)
            }
        }
        tv_login.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        }
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}