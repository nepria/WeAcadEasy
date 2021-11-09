package com.example.weacadeasy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.acadeasy.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val userId = intent.getStringExtra("user_id")
//        val emailId = intent.getStringExtra("email_id")
//
//        uid.text = "User id :: $userId"
//        email.text = "password :: $emailId"

        btnlogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, loginactivity::class.java))
            finish()
        }

        val sharedPreferences = getSharedPreferences(Constants.ACADEASY_PREFERENCES, MODE_PRIVATE)
        val username = sharedPreferences.getString(Constants.LOGGED_IN_USERNAME, "")!!
        val email1 = sharedPreferences.getString(Constants.LOGGED_IN_EMAIL, "")!!
        val number = sharedPreferences.getString(Constants.LOGGED_IN_NUM, "")!!
        uid.text = "User name - $username"
        email.text = "Email id - $email1"
        phonenum.text = "Phone number - $number"


    }
}
