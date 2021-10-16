package com.example.weacadeasy.firestore
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.weacadeasy.Constants
import com.example.weacadeasy.Createacc
import com.example.weacadeasy.MainActivity
import com.example.weacadeasy.loginactivity
import com.example.weacadeasy.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class Firestoreclass {


     private val mFireStore = FirebaseFirestore.getInstance()

     fun registerUser(activity: Createacc, userInfo: User) {
          mFireStore.collection("users")
               .document(userInfo.id)
               .set(userInfo, SetOptions.merge())
               .addOnSuccessListener {
                    activity.userRegistrationSuccess()
               }
     }
       fun getCurrentUserID(): String {
            val currentUser = FirebaseAuth.getInstance().currentUser
            var currentUserID = ""
            if (currentUser != null) {
                 currentUserID = currentUser.uid
            }
            return currentUserID
       }
     fun getUserDetails(activity: Activity) {
          mFireStore.collection(("users"))
               .document(getCurrentUserID())
               .get()
               .addOnSuccessListener{ document ->
                    val user = document.toObject(User::class.java)!!

                    val sharedpreferences = activity.getSharedPreferences(
                         Constants.ACADEASY_PREFERENCES,
                         Context.MODE_PRIVATE
                    )
                    val editor: SharedPreferences.Editor = sharedpreferences.edit()
                    editor.putString(
                         Constants.LOGGED_IN_USERNAME,
                         "${user.firstName}"
                    )
                    editor.putString(
                         Constants.LOGGED_IN_EMAIL,
                         "${user.email}"
                    )
                    editor.putString(
                         Constants.LOGGED_IN_NUM,
                         "${user.mobilenum}"
                    )
                    editor.apply()
                    editor.commit()
                    when (activity) {
                         is loginactivity -> activity.userLoggedInSuccess(user)
                    }
               }
     }
}
