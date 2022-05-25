package com.example.campuslyfe.fragment.etkinlikler

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.campuslyfe.model.Etkinlik
import com.example.campuslyfe.utils.getDatabaseInstance
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class EtkinliklerViewModel : ViewModel() {

    val etkinlikler = MutableLiveData<List<Etkinlik?>>()

    private fun getEtkinlikler() {
        getDatabaseInstance().getReference("Etkinlikler").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    etkinlikler.postValue(
                        snapshot.children.map {
                            it.getValue(Etkinlik::class.java)
                        }
                    )
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("hataaa $error")
                error.toException().printStackTrace()
            }

        })
    }

    init {
        getEtkinlikler()
    }
}