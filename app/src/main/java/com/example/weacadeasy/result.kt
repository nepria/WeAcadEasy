package com.example.weacadeasy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.acadeasy.R
import kotlinx.android.synthetic.main.activity_result.*

class result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val resultname = intent.getStringExtra("result_name")
        resultname1.text = resultname

        get_info.setOnClickListener{
            startActivity(Intent(this, german::class.java))
        }
        profile.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}