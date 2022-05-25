package com.example.campuslyfe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Bina(
    val etkinliklerList: ArrayList<Etkinlik> ?=null,
    val topulukList: ArrayList<Club>?=null,
    val binaAd: String?=null,
    val binalat: Double?=null,
    val binalng: Double?=null
) : Parcelable, Serializable
