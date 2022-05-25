package com.example.campuslyfe.fragment.admin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.campuslyfe.model.Yemek

class YemekhaneDuzenleViewModel: ViewModel() {
    val name = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val yemekList = MutableLiveData<ArrayList<Yemek>>()
}