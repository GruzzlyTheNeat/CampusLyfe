package com.example.campuslyfe.ayarlar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.campuslyfe.utils.StateResource
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class SifreDegistirViewModel : ViewModel() {

    val currentPassword = MutableLiveData<String>()
    val newPassword = MutableLiveData<String>()
    val passwordUpdateState = MutableLiveData<StateResource>()

    fun updatePassword() {
        passwordUpdateState.value = StateResource.Loading

        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser ?: return

        val credential = EmailAuthProvider.getCredential(
            user.email.toString(),
            currentPassword.value ?: ""
        )
        user.reauthenticate(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                user.updatePassword(newPassword.value ?: "")
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            auth.signOut()
                            passwordUpdateState.postValue(StateResource.Success)
                        }
                    }

            } else {
                passwordUpdateState.postValue(StateResource.Error(it.exception))
            }
        }
    }
}