package com.example.campuslyfe.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.campuslyfe.databinding.ActivityMainBinding
import androidx.navigation.ui.setupWithNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.data.sendToDB
import com.example.campuslyfe.model.Bina
import com.example.campuslyfe.model.Club
import com.example.campuslyfe.model.Etkinlik


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navController: NavController by lazy { findNavController(R.id.fragmentContainerView2) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.bottomNavigation.apply {
            setupWithNavController(navController)
            setOnItemReselectedListener { menuItem ->
                navController.navigate(
                    when (menuItem.itemId) {
                        R.id.mainFragment -> R.id.mainFragment
                        R.id.profilFragment -> R.id.profilFragment
                        else -> R.id.ayarlarFragment
                    }
                )
            }
        }
        val etkinlikDeneme = Etkinlik("etkinlik Ad","etkinlik acıklama","etkinlik adres","iletişim bilgileri",2,4.6,6.5)
        val arrayListEtkinlikDeneme = arrayListOf<Etkinlik>()
        val toplulukDeneme = Club(1,"club ad","club discription","binaAd","contactInformation",5.2,6.4)
        val arrayListClubDeneme = arrayListOf<Club>()
        arrayListEtkinlikDeneme.add(etkinlikDeneme)
        arrayListClubDeneme.add(toplulukDeneme)
        sendToDB().sendBina(
        Bina(
            arrayListEtkinlikDeneme,
            arrayListClubDeneme,
            "Bilgisayar Mühendisliği",
            38.45787998795536, 27.213112255208536
        )
        )
        sendToDB().sendBina(
            Bina(
                arrayListEtkinlikDeneme,
                arrayListClubDeneme,
                "Diş Hekimliği Fakültesi",
                38.45620039236553, 27.225188752329437)
        )


    }

}

