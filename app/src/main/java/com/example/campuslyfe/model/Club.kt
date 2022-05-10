package com.example.campuslyfe.model

import android.location.Address
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Club (

    val vektor : Int? = null,
    val name : String? = null,
    val discription : String? = null,
    val address : String? = null,
    val contactInformation : String? = null,
    val ClubAdres : String? = null

) : Parcelable