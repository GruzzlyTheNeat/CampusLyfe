package com.example.campuslyfe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(var adSoyad : String , var bolum : String , var bilgi : String) : Parcelable
