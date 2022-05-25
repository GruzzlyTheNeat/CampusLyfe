package com.example.campuslyfe.data

import com.example.campuslyfe.model.*
import com.example.campuslyfe.utils.getDatabaseInstance


class grabEtkinlik(){
    val databaseEtkinlik = getDatabaseInstance().getReference("Etkinlikler")
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

//class grapProfil(){
//    val databaseUser = FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
//    lateinit var user : User
//    fun asOne(userId: String) : User{
//        databaseUser.child(userId).get().addOnSuccessListener {
//            if(it.exists()){
//                val userAdSoyad = it.child("adSoyad").value.toString()
//                val userBolum = it.child("bolum").value.toString()
//                val userBilgi = it.child("bilgi").value.toString()
//                val user = User(userAdSoyad,userBolum,userBilgi)
//
//            }else{
//
//
//            }
//        }
//        return user
//    }
//
//}

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
    val databaseClub = getDatabaseInstance().getReference("Clublar")
    fun asList(){

    }
//    fun asOne(clubAdTemp: String): Club{
//        lateinit var club: Club
//        databaseClub.child(clubAdTemp).get().addOnSuccessListener {
//            if(it.exists()){
//                val vektor = it.child("vektor").value.toString().toInt()
//                val name = it.child("name").value.toString()
//                val discription = it.child("discription").value.toString()
//                val address = it.child("address").value.toString()
//                val contactInformation = it.child("contactInformation").value.toString()
//                val ClubAdres = it.child("ClubAdres").value.toString()
//                club = Club(vektor, name, discription,address, contactInformation, ClubAdres)
//            }
//            else{
//
//            }
//        }
//        return club
//    }

}

class grabFoodFromDB(){
    val databaseFood = getDatabaseInstance().getReference("Yemekhane")

    fun asOne(haneIsmi: String): Yemekhane{
        lateinit var yemekhane: Yemekhane
        databaseFood.child(haneIsmi).get().addOnSuccessListener {
            if(it.exists()){
                val name= it.child("name").value.toString()
                val haftalık = it.child("haftalık").value
                println("Buraya girdi")
                yemekhane = Yemekhane(name, haftalık as ArrayList<Yemek>)
            }
            else{

            }

        }
        return yemekhane
    }
}

