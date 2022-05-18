package com.example.campuslyfe.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class grabImgFromDB {

    fun grabImg(name: String): Bitmap{
        lateinit var image : Bitmap
        val storageRef = FirebaseStorage.getInstance().reference.child("images/$name.jpg")
        val localFile = File.createTempFile("tempImg","jpg")
        storageRef.getFile(localFile).addOnSuccessListener {

            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            image = bitmap
        }.addOnFailureListener{
            println("Error on grabbing bitmap")
        }
        return image
    }
}