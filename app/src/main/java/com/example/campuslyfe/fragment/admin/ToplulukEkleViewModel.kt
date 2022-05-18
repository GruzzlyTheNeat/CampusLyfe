package com.example.campuslyfe.fragment.admin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToplulukEkleViewModel : ViewModel() {

    val toplulukAdi = MutableLiveData<String>()
    val toplulukAciklama = MutableLiveData<String>()
    val binaAdi = MutableLiveData<String>()
    val iletisimBilgileri = MutableLiveData<String>()
    val lat = MutableLiveData<Double>()
    val lng = MutableLiveData<Double>()

}