package com.example.weacadeasy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import com.example.acadeasy.R
import com.example.weacadeasy.firestore.Firestoreclass
import com.example.weacadeasy.models.User
import com.example.weacadeasy.models.picenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_createacc.*
import kotlinx.android.synthetic.main.activity_loginactivity.*

class loginactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)
        val textView = findViewById<TextView>(R.id.signuptxt) as TextView
        textView.setOnClickListener {
            val intent = Intent(this, Createacc::class.java)
            startActivity(intent)
        }
        loginbtn.setOnClickListener {
            logInRegisteredUser()
        }
    }
       private fun validateLoginDetails(): Boolean {
           return when {
               TextUtils.isEmpty(et_name.text.toString().trim { it <= ' ' }) -> {
                   Toast.makeText(
                       this,
                       "Please enter email.",
                       Toast.LENGTH_SHORT
                   ).show()
                   false
               }
               TextUtils.isEmpty(et_pass.text.toString().trim { it <= ' ' }) -> {
                   Toast.makeText(
                       this,
                       "Please enter password",
                       Toast.LENGTH_SHORT
                   ).show()
                   false
               }
               else -> {
                   true
               }
           }
        }

    private fun logInRegisteredUser() {
        if(validateLoginDetails()) {
            val email = et_name.text.toString().trim { it <= ' '}
            val password = et_pass.text.toString().trim { it <= ' '}

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful) {
                       Firestoreclass().getUserDetails(this)
                        Toast.makeText(
                            this,
                            "You are logged in successfully",
                            Toast.LENGTH_SHORT
                        ).show()
//
//                        val intent =
//                                        Intent(this, MainActivity::class.java)
//                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                     intent.putExtra(Constants.EXTRA_USER_DETAILS, firebaseUser)
//                                    intent.putExtra("user_id", firebaseUser.uid)
//                                    intent.putExtra("email_id", email)
//                                    startActivity(intent)
//                                    finish()
                    } else {
                        Toast.makeText(
                            this,
                            "Login NOT successful!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
    fun userLoggedInSuccess(user: User) {
        val intent =
            Intent(this, picenter::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra(Constants.EXTRA_USER_DETAILS, user)
        startActivity(intent)
    }
    }


