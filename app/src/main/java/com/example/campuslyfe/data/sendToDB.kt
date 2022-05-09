package com.example.campuslyfe.data

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.FirebaseDatabase
import com.example.campuslyfe.model.Etkinlik as Etkinlik
import com.example.campuslyfe.model.Club as Club
import com.example.campuslyfe.model.Bina as Bina

class sendToDB {

    fun sendEtkinlik(etkinlikSend: Etkinlik){
        val database = FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Etkinlikler")
        database.child(etkinlikSend.etkinlikAd).setValue(etkinlikSend).addOnSuccessListener {
            print("Done")
        }.addOnFailureListener{
            print("Failed")
        }

    }

    fun sendClub(clubSend: Club){

        val database = FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Clublar")
        database.child(clubSend.name).setValue(clubSend).addOnSuccessListener {
            print("Done")
        }.addOnFailureListener{
            print("Failed")
        }

    }

    fun sendBina(binaSend: Bina){
        val database = FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Binalar")
        database.child(binaSend.binaAd).setValue(binaSend).addOnSuccessListener {
            print("Done")
        }.addOnFailureListener{
            print("Failed")
        }
    }
}