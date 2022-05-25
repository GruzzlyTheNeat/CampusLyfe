package com.example.campuslyfe.utils

sealed class StateResource {
    object Success : StateResource()
    data class Error(val e: Exception?) : StateResource()
    object Loading : StateResource()
}