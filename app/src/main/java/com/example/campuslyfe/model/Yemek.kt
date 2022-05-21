package com.example.campuslyfe.model

data class Yemek(
    val gunname: String,
    val sabah: String,
    val ogle: String,
    val aksam: String
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
}