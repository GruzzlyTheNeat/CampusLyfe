package com.example.campuslyfe.model

import com.google.firebase.database.Exclude

data class Yemek(
    val gunname: String ?= null,
    val sabah: String ?= null,
    val ogle: String ?= null,
    val aksam: String ?= null
){
    fun toStringSabah(): String {
        return "Sabah Kahvaltısı:\n$sabah"
    }
    fun toStringOgle(): String {
        return "Öğle Yemeği:\n$ogle"
    }
    fun toStringAksam(): String {
        return "Akşam Yemeği:\n$aksam"
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "gunname" to gunname,
            "sabah" to sabah,
            "ogle" to ogle,
            "aksam" to aksam,
        )
    }
}