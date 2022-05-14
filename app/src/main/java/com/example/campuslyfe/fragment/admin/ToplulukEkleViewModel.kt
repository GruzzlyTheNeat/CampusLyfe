package com.example.campuslyfe.fragment.admin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToplulukEkleViewModel : ViewModel() {

    val etkinlikAdi = MutableLiveData<String>()
    val etkinlikAciklama = MutableLiveData<String>()
    val binaAdi = MutableLiveData<String>()
    val iletisimBilgileri = MutableLiveData<String>()
    val lat = MutableLiveData<Double>()
    val lng = MutableLiveData<Double>()

}