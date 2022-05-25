package com.example.campuslyfe.data

import com.example.campuslyfe.model.User
import com.example.campuslyfe.utils.getDatabaseInstance
import com.example.campuslyfe.model.Etkinlik as Etkinlik
import com.example.campuslyfe.model.Club as Club
import com.example.campuslyfe.model.Bina as Bina
import com.example.campuslyfe.model.Yemekhane as Yemekhane

class sendToDB {

    fun sendEtkinlik(etkinlikSend: Etkinlik){
        val database = getDatabaseInstance().getReference("Etkinlikler")
        database.child(etkinlikSend.etkinlikAd.toString()).setValue(etkinlikSend).addOnSuccessListener {
            print("Done")
        }.addOnFailureListener{
            print("Failed")
        }

    }

    fun sendClub(clubSend: Club){

        val database = getDatabaseInstance().getReference("Clublar")
        database.child(clubSend.name.toString()).setValue(clubSend).addOnSuccessListener {
            print("Done")
        }.addOnFailureListener{
            print("Failed")
        }

    }

    fun sendTopluluk(clubSend: Club){

        val database = getDatabaseInstance().getReference("Topluluklar")
        database.child(clubSend.name.toString()).setValue(clubSend).addOnSuccessListener {
            print("Done")
        }.addOnFailureListener{
            print("Failed")
        }

    }

    fun sendBina(binaSend: Bina){
        val database = getDatabaseInstance().getReference("Binalar")
        database.child(binaSend.binaAd.toString()).setValue(binaSend).addOnSuccessListener {
            print("Done")
        }.addOnFailureListener{
            print("Failed")
        }
    }

    fun sendUser(userSend: User,uid : String){
        val database = getDatabaseInstance().getReference("Users")
        database.child(uid).setValue(userSend).addOnSuccessListener {
            print("Done")
        }.addOnFailureListener{
            print("Failed")
        }
    }

    fun sendFood(foodSend: Yemekhane){
        val database = getDatabaseInstance().getReference("Yemekhane")
        database.child(foodSend.name.toString()).setValue(foodSend).addOnSuccessListener {
            print("Done")
        }.addOnFailureListener{
            print("Failed")
        }
    }

}