package com.example.weacadeasy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.acadeasy.R
import com.example.weacadeasy.models.picenter
import com.google.firebase.auth.FirebaseAuth

class firstsplash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firstsplash)
        val mAuth = FirebaseAuth.getInstance()
        fun AppCompatActivity.makeItFullScreenStatusBarVisible(){
            supportActionBar?.hide()
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)}
        val time : Long = 2000
        Handler().postDelayed({
            if (mAuth!!.currentUser == null)
                startActivity(Intent(this, welcome::class.java))
            else
                startActivity(Intent(this, picenter::class.java))
            finish()
        }, time)
    }
}