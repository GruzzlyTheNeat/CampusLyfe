package com.example.campuslyfe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val userId : String ?=null,
    val adSoyad: String? = null,
    val bolum: String? = null,
    val bilgi: String? = null,
    val katildigiEtkinlikler : ArrayList<Etkinlik> ?=null,
    val bulunduguTopluluklar : ArrayList<Club> ?= null
) : Parcelable