package com.example.campuslyfe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Etkinlik(
    val etkinlikAd: String? = null,
    val etkinlilkAciklama: String? = null,
    val etkinlikAdres: String? = null,
    val etkinlikIletisimBilgisi: String? = null,
    val etkinlikPoster: Int? = null

) : Parcelable
