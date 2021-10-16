package com.example.weacadeasy.models
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
class User (
    val id : String = "",
    val firstName: String = "",
    val email: String = "",
    val mobilenum: String = "",
    val profileCompleted: Int = 0
        ): Parcelable