package com.example.campuslyfe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LatLng(
    val lat : Double,
    val lng : Double
):Parcelable
