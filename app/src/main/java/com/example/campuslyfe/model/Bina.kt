package com.example.campuslyfe.model

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


@Parcelize
data class Bina(
    val etkinliklerList: List<Etkinlik>,
    val topulukList: List<Club>,
    val binaAd: String,
    val latLng: LatLng
) : Parcelable, Serializable
