package com.example.campuslyfe.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase

fun getDatabaseInstance() =
    FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/")

fun Fragment.showToast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}