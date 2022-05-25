package com.example.campuslyfe.fragment.signIn

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.campuslyfe.utils.StateResource
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInSignUpViewModel : ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val validateEmail = MutableLiveData<Boolean>()

    init {
        email.observeForever {
            validateEmail.postValue(Patterns.EMAIL_ADDRESS.matcher(it).matches())
        }
    }

    val showEmailError = MutableLiveData(false)


    val signInState = MutableLiveData<StateResource>()
    fun signIn() {
        val email = email.value?.trim()
        val pass = password.value?.trim()

        if (email != null && pass != null) {
            signInState.postValue(StateResource.Loading)
            Firebase.auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener {
                    signInState.postValue(
                        if (it.isSuccessful) StateResource.Success
                        else StateResource.Error(it.exception)
                    )
                }
        }
    }

    val signUpState = MutableLiveData<StateResource>()
    fun signUp() {
        val email = email.value?.trim()
        val pass = password.value?.trim()

        if (email != null && pass != null) {
            signInState.postValue(StateResource.Loading)
            Firebase.auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener {
                    signInState.postValue(
                        if (it.isSuccessful) StateResource.Success
                        else StateResource.Error(it.exception)
                    )
                }
        }
    }
}