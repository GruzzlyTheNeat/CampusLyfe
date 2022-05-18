package com.example.campuslyfe.fragment.admin

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToplulukEkleViewModel : ViewModel() {

    val toplulukAdi = MutableLiveData<String>()
    val toplulukAciklama = MutableLiveData<String>()
    val binaAdi = MutableLiveData<String>()
    val iletisimBilgileri = MutableLiveData<String>()
    val lat = MutableLiveData<Double>()
    val lng = MutableLiveData<Double>()

    val clickListener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            binaAdi.value = p0?.getItemAtPosition(p2) as String
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

    }

}