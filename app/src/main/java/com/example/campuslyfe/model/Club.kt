package com.example.campuslyfe.model

import android.location.Address
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Club (

    val vektor : Int? = null,
    val name : String? = null,
    val discription : String? = null,
    val binaAd : String? = null,
    val contactInformation : String? = null,
    val lat : Double? = null,
    val lng : Double? = null


) : Parcelable