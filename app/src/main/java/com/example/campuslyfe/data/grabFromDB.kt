package com.example.campuslyfe.data

import com.example.campuslyfe.model.Etkinlik
import com.example.campuslyfe.model.Club
import com.example.campuslyfe.model.Bina
import com.example.campuslyfe.model.User
import com.google.firebase.database.FirebaseDatabase
import com.google.android.gms.maps.model.LatLng


class grabEtkinlik(){
    val databaseEtkinlik = FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Etkinlikler")
    val databaseProfil = FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
    fun asList(){

    }
    fun asOne(etkinlikAdTemp: String): Etkinlik{
        lateinit var etkinlik: Etkinlik
        databaseEtkinlik.child(etkinlikAdTemp).get().addOnSuccessListener {
            if(it.exists()){
                val etkinlikAd = it.child("etkinlikAd").value.toString()
                val etkinlikAciklama = it.child("etkinlikAciklama").value.toString()
                val etkinlikAdres = it.child("etkinlikAdres").value.toString()
                val etkinlikIletisimBilgisi = it.child("etkinlikIletisimBilgisi").value.toString()
                val etkinlikPoster = it.child("etkinlikPoster").value.toString().toInt()
                etkinlik = Etkinlik(etkinlikAd, etkinlikAciklama, etkinlikAdres,etkinlikIletisimBilgisi, etkinlikPoster)
            }
            else{

            }
        }
        return etkinlik
    }


    }

/*
class grabBina(){
    val databaseBina = FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Binalar")
    fun asList(){

    }
    fun asOne(binaAdTemp: String): Bina{
        lateinit var binaTemp: Bina
        databaseBina.child(binaAdTemp).get().addOnSuccessListener {
            if(it.exists()){
                val binaAd = it.child("binaAd").value.toString()
                val latLng = it.child("latLng").value.toString()
                val etkinliklerList = it.child("etkinliklerList").value
                val topulukList = it.child("topulukList").value
                val binaTemp = Bina(etkinliklerList as List<Etkinlik>, topulukList as List<Club>, binaAd, latLng)
            }
            else{

            }
        }
        return binaTemp

    }

}
*/
class grabClub(){
    val databaseClub = FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Clublar")
    fun asList(){

    }
    fun asOne(clubAdTemp: String): Club{
        lateinit var club: Club
        databaseClub.child(clubAdTemp).get().addOnSuccessListener {
            if(it.exists()){
                val vektor = it.child("vektor").value.toString().toInt()
                val name = it.child("name").value.toString()
                val discription = it.child("discription").value.toString()
                val address = it.child("address").value.toString()
                val contactInformation = it.child("contactInformation").value.toString()
                val ClubAdres = it.child("ClubAdres").value.toString()
                club = Club(vektor, name, discription,address, contactInformation, ClubAdres)
            }
            else{

            }
        }
        return club
    }

}

