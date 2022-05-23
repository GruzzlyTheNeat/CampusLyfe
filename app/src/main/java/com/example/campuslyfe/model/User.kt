package com.example.campuslyfe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.net.URI

@Parcelize
data class User( val adSoyad: String? = null, val bolum: String? = null, val bilgi: String? = null) : Parcelable
