package com.example.campuslyfe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Club (

    val name : String? = null,
    val discription : String? = null,
    val binaAd : String? = null,
    val contactInformation : String? = null,
    val lat : Double? = null,
    val lng : Double? = null,
    val katilimciListesiTopluluk  : ArrayList<User> ?= null
) : Parcelable