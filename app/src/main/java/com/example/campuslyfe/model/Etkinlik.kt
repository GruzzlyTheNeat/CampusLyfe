package com.example.campuslyfe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Etkinlik(
    val etkinlikAd: String,
    val etkinlilkAciklama: String,
    val etkinlikAdres: String,
    val etkinlikIletisimBilgisi: String,
    val etkinlikPoster: Int? = null

) : Parcelable
