package com.example.campuslyfe.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.campuslyfe.databinding.ActivityMainBinding
import androidx.navigation.ui.setupWithNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.data.sendToDB
import com.example.campuslyfe.model.*


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
        /*
        sendToDB().sendFood(
            Yemekhane(
                "Yemekhane 1",
                arrayListOf(
                    Yemek("Pazartesi", "sabah 1.1", "ogle 1.1","aksam 1.1"),
                    Yemek("Salı", "sabah 1.2", "ogle 1.2","aksam 1.2"),
                    Yemek("Çarşamba", "sabah 1.3", "ogle 1.3","aksam 1.3"),
                    Yemek("Perşembe", "sabah 1.4", "ogle 1.4","aksam 1.4"),
                    Yemek("Cuma", "sabah 1.5", "ogle 1.5","aksam 1.5"),
                    Yemek("Cumartesi", "sabah 1.6", "ogle 1.6","aksam 1.6"),
                    Yemek("Pazar", "sabah 1.7", "ogle 1.7","aksam 1.7")
                )
            )
        )

         */


//        val etkinlikDeneme = Etkinlik("etkinlik Ad","etkinlik acıklama","etkinlik adres","iletişim bilgileri",2,4.6,6.5)
//        val arrayListEtkinlikDeneme = arrayListOf<Etkinlik>()
//        val toplulukDeneme = Club(1,"club ad","club discription","binaAd","contactInformation",5.2,6.4)
//        val arrayListClubDeneme = arrayListOf<Club>()
//        arrayListEtkinlikDeneme.add(etkinlikDeneme)
//        arrayListClubDeneme.add(toplulukDeneme)
//        sendToDB().sendBina(
//        Bina(
//            arrayListEtkinlikDeneme,
//            arrayListClubDeneme,
//            "Bilgisayar Mühendisliği",
//            38.45787998795536, 27.213112255208536
//        )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Diş Hekimliği Fakültesi",
//                38.45620039236553, 27.225188752329437)
//        )
//                sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Kimya Mühendisliği",
//                38.454084499769735, 27.23237715520838)
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Elektrik Elektronik Mühendisliği",
//                38.45340221406766, 27.228662012880662
//            ),
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Yabancı Diller Yüksekokulu",
//                38.456455184294114, 27.228086418439954)
//            )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "2 Nolu Öğrenci Yemekhanesi",
//                38.453668096538614, 27.213011755208363
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "1 Nolu Öğrenci Yemekhanesi",
//                38.457213703124836, 27.228767511027687
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Kütüphane",
//                38.45598240541934, 27.227618539863734
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Egeyurt İzmir Özel Öğrenci Yurdu",
//                38.45297447885302, 27.214885178221135
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi Kapalı Spor Salonu",
//                38.45597092841304, 27.228967026306893
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi Sosyal Bilimler Enstitüsü",
//                38.45763763132211, 27.219289871296393
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Nükleer Bilimler Enstitüsü",
//                38.45680587735455, 27.220674089804444
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi Ziraat Fakültesi",
//                38.45467983095223, 27.225995390712445
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi Hemşirelik Fakültesi",
//                38.45471625319187, 27.216192040293787
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi Temel Bilimler Bölümü",
//                38.45734945309966, 27.2191485166481
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi Fen Bilimleri Enstitüsü",
//                38.454240668587474, 27.226976849451855
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi 20 Mayıs Spor Tesisleri",
//                38.45454883958496, 27.22866244944754
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi Prof Dr Sermed Akgün Olimpik Kapalı Yüzme Havuzu",
//                38.456890965462044, 27.229718791634905
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi Biyomühendislik Bölümü",
//                38.45549943574219, 27.232728974167546
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi Biyoloji Bölümü",
//                38.45742370554574, 27.231033649334677
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "EÜ Prof Dr Yusuf Vardar MÖTBE Kültür Merkezi",
//                38.45802949616403, 27.23173937443422
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "55 Ege Kampüs Tören Şölen Alanı",
//                38.45733245562207, 27.224585116154476
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi Gıda Mühendisliği Bölümü",
//                38.453413898569174, 27.22987693701534
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi Tekstil Mühendisliği Bölümü",
//                38.45274546441227, 27.232077903622933
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi Eğitim Fakültesi",
//                38.459267318017865, 27.222299373134163
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi Tıp Fakültesi Hastanesi",
//                38.45678686009291, 27.210276882907664
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi Tıp Fakültesi Derslikleri ve Kütüphanesi",
//                38.454832352912966, 27.212753011658513
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Poliklinikler",
//                38.4563431788653, 27.21177948209719
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi Hastanesi Acil Servis",
//                38.45712716189004, 27.209812276505726
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi İktisadi ve İdari Billimler Fakültesi",
//                38.45790537825317, 27.214952482406844
//            )
//        )
//        sendToDB().sendBina(
//            Bina(
//                arrayListEtkinlikDeneme,
//                arrayListClubDeneme,
//                "Ege Üniversitesi Basım ve Yayınevi Şube Müdürlüğü",
//                38.457293635968504, 27.21840893941704
//            )
//        )



    }

}

