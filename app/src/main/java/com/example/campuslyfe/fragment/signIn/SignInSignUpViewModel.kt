package com.example.campuslyfe.fragment.signIn

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
}