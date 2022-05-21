package com.example.campuslyfe.fragment.harita

import com.example.campuslyfe.fragment.etkinlikler.EtkinliklerAdapter
import com.example.campuslyfe.model.Bina
import com.example.campuslyfe.model.Etkinlik
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HaritaRepository {

    val binalar = listOf(
        Bina(
            ArrayList(),
            ArrayList(),
            "Bilgisayar Mühendisliği",
            38.45787998795536, 27.213112255208536
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Diş Hekimliği Fakültesi",
            38.45620039236553, 27.225188752329437
        ),

        Bina(
            ArrayList(),
            ArrayList(),
            "Kimya Mühendisliği",
            38.454084499769735, 27.23237715520838
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Elektrik Elektronik Mühendisliği",
            38.45340221406766, 27.228662012880662
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Yabancı Diller Yüksekokulu",
            38.456455184294114, 27.228086418439954
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "2 Nolu Öğrenci Yemekhanesi",
            38.453668096538614, 27.213011755208363
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "1 Nolu Öğrenci Yemekhanesi",
            38.457213703124836, 27.228767511027687
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Kütüphane",
            38.45598240541934, 27.227618539863734
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Egeyurt İzmir Özel Öğrenci Yurdu",
            38.45297447885302, 27.214885178221135
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi Kapalı Spor Salonu",
            38.45597092841304, 27.228967026306893
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi Sosyal Bilimler Enstitüsü",
            38.45763763132211, 27.219289871296393
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Nükleer Bilimler Enstitüsü",
            38.45680587735455, 27.220674089804444
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi Ziraat Fakültesi",
            38.45467983095223, 27.225995390712445
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi Hemşirelik Fakültesi",
            38.45471625319187, 27.216192040293787
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi Temel Bilimler Bölümü",
            38.45734945309966, 27.2191485166481
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi Fen Bilimleri Enstitüsü",
            38.454240668587474, 27.226976849451855
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi 20 Mayıs Spor Tesisleri",
            38.45454883958496, 27.22866244944754
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi Prof Dr Sermed Akgün Olimpik Kapalı Yüzme Havuzu",
            38.456890965462044, 27.229718791634905
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi Biyomühendislik Bölümü",
            38.45549943574219, 27.232728974167546
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi Biyoloji Bölümü",
            38.45742370554574, 27.231033649334677
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "EÜ Prof Dr Yusuf Vardar MÖTBE Kültür Merkezi",
            38.45802949616403, 27.23173937443422
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "55 Ege Kampüs Tören Şölen Alanı",
            38.45733245562207, 27.224585116154476
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi Gıda Mühendisliği Bölümü",
            38.453413898569174, 27.22987693701534
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi Tekstil Mühendisliği Bölümü",
            38.45274546441227, 27.232077903622933
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi Eğitim Fakültesi",
            38.459267318017865, 27.222299373134163
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi Tıp Fakültesi Hastanesi",
            38.45678686009291, 27.210276882907664
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi Tıp Fakültesi Derslikleri ve Kütüphanesi",
            38.454832352912966, 27.212753011658513
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Poliklinikler",
            38.4563431788653, 27.21177948209719
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi Hastanesi Acil Servis",
            38.45712716189004, 27.209812276505726
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi İktisadi ve İdari Billimler Fakültesi",
            38.45790537825317, 27.214952482406844
        ),
        Bina(
            ArrayList(),
            ArrayList(),
            "Ege Üniversitesi Basım ve Yayınevi Şube Müdürlüğü",
            38.457293635968504, 27.21840893941704
        )

    )





}