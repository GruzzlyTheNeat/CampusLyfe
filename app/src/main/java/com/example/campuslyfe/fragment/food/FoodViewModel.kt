package com.example.campuslyfe.fragment.food

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.campuslyfe.model.Yemek
import com.example.campuslyfe.model.Yemekhane
import com.example.campuslyfe.utils.getDatabaseInstance
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class FoodViewModel : ViewModel() {
    val selectedYemekhane = MutableLiveData<String>()
    val yemekhaneIndex = MutableLiveData<Int>()

    val selectedGun = MutableLiveData<String>()
    val selectedGunIndex = MutableLiveData<Int>()

    init {
        selectedGun.observeForever {
            selectedGunIndex.postValue(
                when (it) {
                    "Pazartesi" -> 0
                    "Salı" -> 1
                    "Çarşamba" -> 2
                    "Perşembe" -> 3
                    "Cuma" -> 4
                    "Cumartesi" -> 5
                    else -> 6
                }
            )
        }
        selectedYemekhane.observeForever {
            yemekhaneIndex.postValue(
                when (it) {
                    "Yemekhane 1" -> 0
                    "Yemekhane 2" -> 1
                    else -> 2
                }
            )
        }
    }

    val yemekListesi = MutableLiveData<Yemek>()

    fun yemekleriGetir() {
        getDatabaseInstance().getReference("Yemekhane")
            .addValueEventListener(object : ValueEventListener  {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (!snapshot.exists())
                        return

                    yemekListesi.postValue(
                        snapshot.children.map {
                            it.getValue(Yemekhane::class.java)
                        }.getOrNull(yemekhaneIndex.value!!)?.haftalık?.get(
                            selectedGunIndex.value!!
                        )
                    )
                }

                override fun onCancelled(error: DatabaseError) {
                    error.toException().printStackTrace()
                }

            })
    }
}