package com.example.campuslyfe.fragment.profil

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.campuslyfe.utils.getDatabaseInstance
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage

class ProfilViewModel : ViewModel() {

    private val uid = FirebaseAuth.getInstance().currentUser?.uid

    val ad = MutableLiveData<String>()
    val bolum = MutableLiveData<String>()
    val bilgiGiris = MutableLiveData<String>()
    val imageUrl = MutableLiveData<String>()

    private fun getProfile() {
        getDatabaseInstance().getReference("Users")
            .child(uid.toString()).get().addOnSuccessListener {
                if (it.exists()) {
                    ad.postValue(it.child("adSoyad").value.toString())
                    bolum.postValue(it.child("bolum").value.toString())
                    bilgiGiris.postValue(it.child("bilgi").value.toString())
                }
            }
    }

    private fun getProfilePic() {
        if (uid != null) {
            val imagePath = "users/$uid"
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child(imagePath)
            imageRef.downloadUrl.addOnSuccessListener {
                imageUrl.postValue(it.toString())
            }
        }
    }

    init {
        getProfile()
        getProfilePic()
    }

}