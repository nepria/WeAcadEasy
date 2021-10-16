package com.example.weacadeasy
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.example.acadeasy.R
import android.text.TextUtils
import android.widget.Toast
import com.example.weacadeasy.firestore.Firestoreclass
import com.example.weacadeasy.models.User
import kotlinx.android.synthetic.main.activity_createacc.*

class Createacc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createacc)
        logintxt.setOnClickListener {
            val intent = Intent(this, loginactivity::class.java)
            startActivity(intent)
            finish()
        }

        registersignup.setOnClickListener {
            when {
                TextUtils.isEmpty(et_register_name.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Createacc,
                        "Please enter name.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(et_register_email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Createacc,
                        "Please enter email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(et_register_password.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Createacc,
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String = et_register_email.text.toString().trim { it <= ' ' }
                    val password: String = et_register_password.text.toString().trim { it <= ' ' }
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->
                                if (task.isSuccessful) {

                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    val user = User(
                                        firebaseUser.uid,
                                        et_register_name.text.toString().trim { it <= ' ' },
                                        et_register_email.text.toString().trim { it <= ' ' },
                                        et_register_phone.text.toString().trim(){ it <= ' '}
                                    )
                                    Toast.makeText(
                                        this,
                                        "You are  registered successfully.",
                                        Toast.LENGTH_SHORT
                                    ).show()
//                                    val intent =
//                                        Intent(this@Createacc, MainActivity::class.java)
//                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                    intent.putExtra("user_id", firebaseUser.uid)
//                                    intent.putExtra("user_id", email)
//                                    startActivity(intent)
//                                    finish()
                                    Firestoreclass().registerUser(this, user)
                                    FirebaseAuth.getInstance().signOut()
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                }
            }
        }
        }

    fun userRegistrationSuccess() {
        Toast.makeText(
            this,
            "Registration is successful",
            Toast.LENGTH_SHORT
        ).show()
    }
}









