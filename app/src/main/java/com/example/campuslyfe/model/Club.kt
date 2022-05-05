package com.example.campuslyfe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Club (

    val vektor : Int,
    val name : String,
    val discription : String,
    val address : String,
    val contactInformation : String

) : Parcelable