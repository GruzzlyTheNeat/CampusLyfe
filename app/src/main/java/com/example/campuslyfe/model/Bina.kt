package com.example.campuslyfe.model

import android.location.Address
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Bina(
    val etkinliklerList : List<Etkinlik>,
    val topulukList : List<Club>,
    val binaAd : String
):Parcelable
