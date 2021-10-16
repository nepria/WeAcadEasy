package com.example.weacadeasy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.acadeasy.R
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_welcome.*

class welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val mAuth = FirebaseAuth.getInstance()
        clickmain.setOnClickListener{
            if (mAuth!!.currentUser == null)
                startActivity(Intent(this, loginactivity::class.java))
            else
                startActivity(Intent(this, MainActivity::class.java))

        }
    }
}

