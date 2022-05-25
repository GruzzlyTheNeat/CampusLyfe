package com.example.campuslyfe.fragment.club

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.campuslyfe.model.Club
import com.example.campuslyfe.utils.getDatabaseInstance
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ClubViewModel : ViewModel() {

    val topluluklar = MutableLiveData<List<Club?>>()

    private fun getTopluluklar() {
        getDatabaseInstance().getReference("Topluluklar")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        topluluklar.postValue(
                            snapshot.children.map {
                                it.getValue(Club::class.java)
                            }
                        )
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    error.toException().printStackTrace()
                }

            })
    }

    init {
        getTopluluklar()
    }
}