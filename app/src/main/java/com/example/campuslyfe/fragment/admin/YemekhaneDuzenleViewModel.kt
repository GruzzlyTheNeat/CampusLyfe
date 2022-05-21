package com.example.campuslyfe.fragment.admin

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.campuslyfe.model.Yemek

class YemekhaneDuzenleViewModel: ViewModel() {
    val name = MutableLiveData<String>()
    val toplulukAciklama = MutableLiveData<ArrayList<Yemek>>()



}