package com.example.campuslyfe.utils

import com.google.firebase.database.FirebaseDatabase

fun getDatabaseInstance() =
    FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/")